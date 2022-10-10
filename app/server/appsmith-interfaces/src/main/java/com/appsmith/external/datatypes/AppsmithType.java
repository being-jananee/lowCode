package com.bizBrainz.external.datatypes;

import com.bizBrainz.external.constants.DataType;

import java.util.function.Predicate;

public interface BizbrainzType extends Predicate<String> {
    String performSmartSubstitution(String value);
    DataType type();
}
