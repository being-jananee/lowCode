package com.bizBrainz.external.datatypes;

import com.bizBrainz.external.constants.DataType;

public class IntegerType implements BizbrainzType{
    @Override
    public String performSmartSubstitution(String s) {
        return s;
    }

    @Override
    public boolean test(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            // Not an integer
        }
        return false;
    }

    @Override
    public DataType type() {
        return DataType.INTEGER;
    }
}
