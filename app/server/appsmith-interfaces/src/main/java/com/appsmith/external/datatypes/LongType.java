package com.bizBrainz.external.datatypes;

import com.bizBrainz.external.constants.DataType;

public class LongType implements BizbrainzType {

    @Override
    public boolean test(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            // Not a long
        }
        return false;
    }

    @Override
    public String performSmartSubstitution(String s) {
        return s;
    }

    @Override
    public DataType type() {
        return DataType.LONG;
    }
}
