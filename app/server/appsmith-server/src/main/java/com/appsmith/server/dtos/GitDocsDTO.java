package com.bizBrainz.server.dtos;

import com.bizBrainz.external.constants.ErrorReferenceDocUrl;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GitDocsDTO {
    ErrorReferenceDocUrl docKey;

    String docUrl;
}
