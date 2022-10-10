package com.bizBrainz.server.exceptions;

import com.bizBrainz.external.exceptions.BizbrainzErrorAction;
import com.bizBrainz.external.exceptions.BaseException;
import com.bizBrainz.external.exceptions.ErrorDTO;
import com.bizBrainz.external.exceptions.pluginExceptions.BizbrainzPluginException;
import com.bizBrainz.server.dtos.ResponseDTO;
import com.bizBrainz.server.filters.MDCFilter;
import io.sentry.Sentry;
import io.sentry.SentryLevel;
import io.sentry.protocol.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBufferLimitException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * This class catches all the Exceptions and formats them into a proper ResponseDTO<ErrorDTO> object before
 * sending it to the client.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private void doLog(Throwable error) {
        log.error("", error);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        error.printStackTrace(printWriter);
        String stringStackTrace = stringWriter.toString();

        Sentry.configureScope(
                scope -> {
                    /**
                     * Send stack trace as a string message. This is a work around till it is figured out why raw
                     * stack trace is not visible on Sentry dashboard.
                     * */
                    scope.setExtra("Stack Trace", stringStackTrace);
                    scope.setLevel(SentryLevel.ERROR);
                    scope.setTag("source", "bizBrainz-internal-server");
                }
        );

        if (error instanceof BaseException) {
            BaseException baseError = (BaseException) error;
            if (baseError.getErrorAction() == BizbrainzErrorAction.LOG_EXTERNALLY) {
                Sentry.configureScope(scope -> {
                    baseError.getContextMap().forEach(scope::setTag);
                });
                final User user = new User();
                user.setEmail(baseError.getContextMap().getOrDefault(MDCFilter.USER_EMAIL, "unknownUser"));
                Sentry.setUser(user);
                Sentry.captureException(error);
            }
        } else {
            Sentry.captureException(error);
        }
    }

    /**
     * This function only catches the BizbrainzException type and formats it into ResponseEntity<ErrorDTO> object
     * Ideally, we should only be throwing BizbrainzException from our code. This ensures that we can standardize
     * and set proper error messages and codes.
     *
     * @param e        BizbrainzException that will be caught by the function
     * @param exchange ServerWebExchange contract in order to extract the response and set the http status code
     * @return Mono<ResponseDto < ErrorDTO>>
     */
    @ExceptionHandler
    @ResponseBody
    public Mono<ResponseDTO<ErrorDTO>> catchBizbrainzException(BizbrainzException e, ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.resolve(e.getHttpStatus()));
        doLog(e);

        // Do special formatting for this error to run the message string into valid jsonified string
        if (BizbrainzError.INVALID_DYNAMIC_BINDING_REFERENCE.getAppErrorCode().equals(e.getError().getAppErrorCode())) {
            return Mono.just(new ResponseDTO<>(e.getHttpStatus(), new ErrorDTO(e.getAppErrorCode(), "{" + e.getMessage() + "}")));
        }

        return Mono.just(new ResponseDTO<>(e.getHttpStatus(), new ErrorDTO(e.getAppErrorCode(), e.getMessage(), e.getErrorType(), e.getReferenceDoc())));
    }

    @ExceptionHandler
    @ResponseBody
    public Mono<ResponseDTO<ErrorDTO>> catchDuplicateKeyException(org.springframework.dao.DuplicateKeyException e, ServerWebExchange exchange) {
        BizbrainzError bizBrainzError = BizbrainzError.DUPLICATE_KEY;
        exchange.getResponse().setStatusCode(HttpStatus.resolve(bizBrainzError.getHttpErrorCode()));
        doLog(e);
        return Mono.just(new ResponseDTO<>(bizBrainzError.getHttpErrorCode(), new ErrorDTO(bizBrainzError.getAppErrorCode(),
                bizBrainzError.getMessage(e.getCause().getMessage()))));
    }

    @ExceptionHandler
    @ResponseBody
    public Mono<ResponseDTO<ErrorDTO>> catchTimeoutException(java.util.concurrent.TimeoutException e, ServerWebExchange exchange) {
        BizbrainzError bizBrainzError = BizbrainzError.PLUGIN_EXECUTION_TIMEOUT;
        exchange.getResponse().setStatusCode(HttpStatus.resolve(bizBrainzError.getHttpErrorCode()));
        doLog(e);
        return Mono.just(new ResponseDTO<>(bizBrainzError.getHttpErrorCode(), new ErrorDTO(bizBrainzError.getAppErrorCode(),
                bizBrainzError.getMessage())));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseBody
    public Mono<ResponseDTO<ErrorDTO>> catchWebExchangeBindException(
            WebExchangeBindException exc, ServerWebExchange exchange) {
        BizbrainzError bizBrainzError = BizbrainzError.VALIDATION_FAILURE;
        exchange.getResponse().setStatusCode(HttpStatus.resolve(bizBrainzError.getHttpErrorCode()));
        Map<String, String> errors = new HashMap<>();
        exc.getBindingResult()
                .getAllErrors()
                .forEach(
                        (error) -> {
                            String fieldName = ((FieldError) error).getField();
                            String errorMessage = error.getDefaultMessage();
                            errors.put(fieldName, errorMessage);
                        });
        return Mono.just(new ResponseDTO<>(bizBrainzError.getHttpErrorCode(), new ErrorDTO(bizBrainzError.getAppErrorCode(),
                bizBrainzError.getMessage(errors.toString()))));
    }


    @ExceptionHandler
    @ResponseBody
    public Mono<ResponseDTO<ErrorDTO>> catchServerWebInputException(ServerWebInputException e, ServerWebExchange exchange) {
        BizbrainzError bizBrainzError = BizbrainzError.GENERIC_BAD_REQUEST;
        exchange.getResponse().setStatusCode(HttpStatus.resolve(bizBrainzError.getHttpErrorCode()));
        doLog(e);
        String errorMessage = e.getReason();
        if (e.getMethodParameter() != null) {
            errorMessage = "Malformed parameter '" + e.getMethodParameter().getParameterName()
                    + "' for " + e.getMethodParameter().getContainingClass().getSimpleName()
                    + (e.getMethodParameter().getMethod() != null ? "." + e.getMethodParameter().getMethod().getName() : "");
        }

        return Mono.just(new ResponseDTO<>(bizBrainzError.getHttpErrorCode(), new ErrorDTO(bizBrainzError.getAppErrorCode(),
                bizBrainzError.getMessage(errorMessage))));
    }

    @ExceptionHandler
    @ResponseBody
    public Mono<ResponseDTO<ErrorDTO>> catchPluginException(BizbrainzPluginException e, ServerWebExchange exchange) {
        BizbrainzError bizBrainzError = BizbrainzError.INTERNAL_SERVER_ERROR;
        exchange.getResponse().setStatusCode(HttpStatus.resolve(bizBrainzError.getHttpErrorCode()));
        doLog(e);
        return Mono.just(new ResponseDTO<>(bizBrainzError.getHttpErrorCode(), new ErrorDTO(bizBrainzError.getAppErrorCode(),
                e.getMessage(), e.getErrorType(), null)));
    }

    @ExceptionHandler
    @ResponseBody
    public Mono<ResponseDTO<ErrorDTO>> catchAccessDeniedException(AccessDeniedException e, ServerWebExchange exchange) {
        BizbrainzError bizBrainzError = BizbrainzError.UNAUTHORIZED_ACCESS;
        exchange.getResponse().setStatusCode(HttpStatus.resolve(bizBrainzError.getHttpErrorCode()));
        doLog(e);
        return Mono.just(new ResponseDTO<>(bizBrainzError.getHttpErrorCode(), new ErrorDTO(bizBrainzError.getAppErrorCode(),
                bizBrainzError.getMessage())));
    }

    @ExceptionHandler
    @ResponseBody
    public Mono<ResponseDTO<ErrorDTO>> catchDataBufferLimitException(DataBufferLimitException e, ServerWebExchange exchange) {
        BizbrainzError bizBrainzError = BizbrainzError.FILE_PART_DATA_BUFFER_ERROR;
        exchange.getResponse().setStatusCode(HttpStatus.resolve(bizBrainzError.getHttpErrorCode()));
        doLog(e);
        return Mono.just(new ResponseDTO<>(bizBrainzError.getHttpErrorCode(), new ErrorDTO(bizBrainzError.getAppErrorCode(),
                bizBrainzError.getMessage(e.getMessage()))));
    }

    /**
     * This function catches the generic Exception class and is meant to be a catch all to ensure that we don't leak
     * any information to the client. Ideally, the function #catchBizbrainzException should be used
     *
     * @param e        Exception that will be caught by the function
     * @param exchange ServerWebExchange contract in order to extract the response and set the http status code
     * @return Mono<ResponseDto < ErrorDTO>>
     */
    @ExceptionHandler
    @ResponseBody
    public Mono<ResponseDTO<ErrorDTO>> catchException(Exception e, ServerWebExchange exchange) {
        BizbrainzError bizBrainzError = BizbrainzError.INTERNAL_SERVER_ERROR;
        exchange.getResponse().setStatusCode(HttpStatus.resolve(bizBrainzError.getHttpErrorCode()));
        doLog(e);
        return Mono.just(new ResponseDTO<>(bizBrainzError.getHttpErrorCode(), new ErrorDTO(bizBrainzError.getAppErrorCode(),
                bizBrainzError.getMessage())));
    }
}
