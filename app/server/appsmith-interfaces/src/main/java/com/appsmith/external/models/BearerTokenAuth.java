package com.bizBrainz.external.models;

import com.bizBrainz.external.annotations.documenttype.DocumentType;
import com.bizBrainz.external.annotations.encryption.Encrypted;
import com.bizBrainz.external.constants.Authentication;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DocumentType(Authentication.BEARER_TOKEN)
public class BearerTokenAuth extends AuthenticationDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Encrypted
    String bearerToken;
}