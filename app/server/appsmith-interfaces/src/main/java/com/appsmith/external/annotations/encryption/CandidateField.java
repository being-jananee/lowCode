package com.bizBrainz.external.annotations.encryption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;

@Getter
@Setter
@AllArgsConstructor
public class CandidateField {
    private Field field;
    private Type type;

    enum Type {
        ANNOTATED_FIELD,
        BIZBRAINZ_FIELD_KNOWN,
        BIZBRAINZ_FIELD_UNKNOWN,
        BIZBRAINZ_FIELD_POLYMORPHIC,
        BIZBRAINZ_COLLECTION_KNOWN,
        BIZBRAINZ_COLLECTION_UNKNOWN,
        BIZBRAINZ_COLLECTION_POLYMORPHIC,
        BIZBRAINZ_MAP_KNOWN,
        BIZBRAINZ_MAP_UNKNOWN,
        BIZBRAINZ_MAP_POLYMORPHIC
    }
}
