package com.bizBrainz.external.helpers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BizbrainzEventContext {
    private final BizbrainzEventContextType bizBrainzEventContextType;
    private final Object[] args;

    public BizbrainzEventContext(BizbrainzEventContextType bizBrainzEventContextType, Object... args) {
        this.bizBrainzEventContextType = bizBrainzEventContextType;
        this.args = args;
    }
}
