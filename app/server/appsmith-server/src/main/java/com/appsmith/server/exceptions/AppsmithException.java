package com.bizBrainz.server.exceptions;

import com.bizBrainz.external.exceptions.BizbrainzErrorAction;
import com.bizBrainz.external.exceptions.BaseException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BizbrainzException extends BaseException {

    private final BizbrainzError error;
    private final transient Object[] args;

    public BizbrainzException(BizbrainzError error, Object... args) {
        super(error.getMessage(args));
        this.error = error;
        this.args = args;
    }

    public Integer getHttpStatus() {
        return this.error == null ? 500 : this.error.getHttpErrorCode();
    }

    @Override
    public String getMessage() {
        return this.error == null ? super.getMessage() : this.error.getMessage(this.args);
    }

    public Integer getAppErrorCode() {
        return this.error == null ? -1 : this.error.getAppErrorCode();
    }

    public BizbrainzErrorAction getErrorAction() {
        return this.error.getErrorAction();
    }

    public String getTitle() {
        return this.error.getTitle();
    }

    public String getErrorType() { return this.error.getErrorType(); }

    public String getReferenceDoc() { return this.error.getReferenceDoc(); }

}
