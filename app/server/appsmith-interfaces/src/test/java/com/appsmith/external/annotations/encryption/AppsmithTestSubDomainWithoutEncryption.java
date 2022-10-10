package com.bizBrainz.external.annotations.encryption;

import com.bizBrainz.external.models.BizbrainzDomain;

import lombok.Data;

@Data
public class BizbrainzTestSubDomainWithoutEncryption  implements BizbrainzDomain {
    String notEncryptedInSubDomain;
}