package com.bizBrainz.external.datatypes;

import com.bizBrainz.external.constants.DataType;

import javax.naming.OperationNotSupportedException;

public class NullType implements BizbrainzType {

    @Override
    public boolean test(String s) {
        if (s == null) {
            return true;
        }
        final String trimmedValue = s.trim();
        return "null".equalsIgnoreCase(trimmedValue);
    }

    @Override
    public String performSmartSubstitution(String s) {
        return null;
    }

    @Override
    public DataType type() {
        return DataType.NULL;
    }
}