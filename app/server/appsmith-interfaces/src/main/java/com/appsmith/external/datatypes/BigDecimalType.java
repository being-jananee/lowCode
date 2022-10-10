package com.bizBrainz.external.datatypes;

import com.bizBrainz.external.constants.DataType;

import java.math.BigDecimal;

public class BigDecimalType implements BizbrainzType{
    @Override
    public String performSmartSubstitution(String s) {
        return s;
    }

    @Override
    public boolean test(String s) {
        try {
            new BigDecimal(s);
            return true;
        } catch (NumberFormatException e) {
            // Not a BigDecimal
        }
        return false;
    }

    @Override
    public DataType type() {
        return DataType.BIGDECIMAL;
    }
}
