package com.bizBrainz.external.exceptions.pluginExceptions;

import com.bizBrainz.external.exceptions.BizbrainzErrorAction;
import com.bizBrainz.external.exceptions.BaseException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BizbrainzPluginException extends BaseException {
    private final Throwable externalError;
    private final BizbrainzPluginError error;
    private final Object[] args;

    public BizbrainzPluginException(BizbrainzPluginError error, Object... args) {
        this(null, error, args);
    }

    public BizbrainzPluginException(Throwable externalError, BizbrainzPluginError error, Object... args) {
        super(error.getMessage(args));
        this.externalError = externalError;
        this.error = error;
        this.args = args;
    }

    public Integer getHttpStatus() {
        return this.error.getHttpErrorCode();
    }

    @Override
    public String getMessage() {
        return this.error.getMessage(args);
    }

    public Integer getAppErrorCode() {
        return this.error == null ? 0 : this.error.getAppErrorCode();
    }

    public BizbrainzErrorAction getErrorAction() {
        return this.error.getErrorAction();
    }

    public String getTitle() { return this.error.getTitle(); }

    public String getErrorType() { return this.error.getErrorType(); }
}
