package com.bizBrainz.external.models;

import com.bizBrainz.external.annotations.documenttype.DocumentType;
import com.bizBrainz.external.annotations.encryption.Encrypted;
import com.bizBrainz.external.constants.Authentication;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DocumentType(Authentication.API_KEY)
public class ApiKeyAuth extends AuthenticationDTO {

    public enum Type {
        @JsonProperty(Authentication.API_KEY_AUTH_TYPE_QUERY_PARAMS)
        QUERY_PARAMS,
        @JsonProperty(Authentication.API_KEY_AUTH_TYPE_HEADER)
        HEADER,
    }

    Type addTo;
    String label;
    String headerPrefix;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Encrypted
    String value;
}