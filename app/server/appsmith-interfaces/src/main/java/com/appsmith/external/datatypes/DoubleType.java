package com.bizBrainz.external.datatypes;

import com.bizBrainz.external.constants.DataType;

public class DoubleType implements BizbrainzType{
    @Override
    public String performSmartSubstitution(String value) {
        return String.valueOf(value);
    }

    @Override
    public boolean test(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            // Not a double
        }
        return false;
    }

    @Override
    public DataType type() {
        return DataType.DOUBLE;
    }
}
