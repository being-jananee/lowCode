package com.bizBrainz.external.models;

import com.bizBrainz.external.annotations.encryption.Encrypted;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Document
public class PEMCertificate implements BizbrainzDomain {

    UploadedFile file;

    @Encrypted
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

}
