package com.bizBrainz.external.models;

import com.bizBrainz.external.annotations.encryption.Encrypted;
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
public class SSHPrivateKey implements BizbrainzDomain {

    UploadedFile keyFile;

    @Encrypted
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

}
