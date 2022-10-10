package com.bizBrainz.external.datatypes;

import com.bizBrainz.external.constants.DataType;

public class BooleanType implements BizbrainzType {

    @Override
    public boolean test(String s) {
        return "true".equalsIgnoreCase(s) || "false".equalsIgnoreCase(s);
    }

    @Override
    public String performSmartSubstitution(String s) {
        return String.valueOf(s);
    }

    @Override
    public DataType type() {
        return DataType.BOOLEAN;
    }
}
