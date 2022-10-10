package com.bizBrainz.server.domains;

import com.bizBrainz.external.annotations.encryption.Encrypted;
import com.bizBrainz.external.models.BizbrainzDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.time.Instant;

@Data
public class GitAuth implements BizbrainzDomain {

    @JsonIgnore
    @Encrypted
    String privateKey;

    String publicKey;

    @JsonIgnore
    Instant generatedAt;

    // Deploy key documentation url
    @Transient
    String docUrl;

    @Transient
    boolean isRegeneratedKey = false;
}
