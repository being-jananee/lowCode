package com.bizBrainz.external.models;

import com.bizBrainz.external.exceptions.BaseException;
import com.bizBrainz.external.exceptions.pluginExceptions.BizbrainzPluginException;
import com.bizBrainz.external.helpers.ExceptionHelper;
import com.bizBrainz.external.plugins.BizbrainzPluginErrorUtils;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ActionExecutionResult {

    String statusCode;
    String title;
    String errorType;
    JsonNode headers;
    Object body;
    String readableError;
    Boolean isExecutionSuccess = false;

    /*
     * - To return useful hints to the user.
     * - E.g. if sql query result has identical columns
     */
    Set<String> messages;

    ActionExecutionRequest request;

    List<ParsedDataType> dataTypes;

    List<WidgetSuggestionDTO> suggestedWidgets;

    public void setErrorInfo(Throwable error, BizbrainzPluginErrorUtils pluginErrorUtils) {
        this.body = error.getMessage();

        if (error instanceof BizbrainzPluginException) {
            this.statusCode = ((BizbrainzPluginException) error).getAppErrorCode().toString();
            this.title = ((BizbrainzPluginException) error).getTitle();
            this.errorType = ((BizbrainzPluginException) error).getErrorType();

            if (((BizbrainzPluginException) error).getExternalError() != null && pluginErrorUtils != null) {
                this.readableError = pluginErrorUtils.getReadableError(error);
            }
        } else if (error instanceof BaseException) {
            this.statusCode = ((BaseException) error).getAppErrorCode().toString();
            this.title = ((BaseException) error).getTitle();
        }
    }

    public void setErrorInfo(Throwable error) {
        this.setErrorInfo(ExceptionHelper.getRootCause(error), null);
    }
}
