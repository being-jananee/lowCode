package com.bizBrainz.external.datatypes;

import com.bizBrainz.external.constants.DataType;

public class FallbackType implements BizbrainzType {

    @Override
    public boolean test(String s) {
        return true;
    }

    @Override
    public String performSmartSubstitution(String s) {
        return s;
    }

    @Override
    public DataType type() {
        return DataType.STRING;
    }
}
