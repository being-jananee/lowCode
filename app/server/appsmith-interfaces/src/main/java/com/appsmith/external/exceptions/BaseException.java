package com.bizBrainz.external.exceptions;

import lombok.Getter;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class BaseException extends RuntimeException {

    private Map<String, String> contextMap;

    public BaseException(String message) {
        super(message);
        contextMap = MDC.getCopyOfContextMap();
        if (contextMap == null) {
            contextMap = new HashMap<>();
        }
    }

    public abstract Integer getHttpStatus();

    public abstract Integer getAppErrorCode();

    public abstract BizbrainzErrorAction getErrorAction();

    public abstract String getTitle();

    public String getMessage() {
        return super.getMessage();
    }

}
