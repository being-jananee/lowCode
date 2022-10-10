package com.bizBrainz.external.models;

import com.bizBrainz.external.annotations.encryption.Encrypted;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse implements BizbrainzDomain {

    @Encrypted
    String token;

    @Encrypted
    String refreshToken;

    Instant issuedAt;

    Instant expiresAt;

    @Encrypted
    Object tokenResponse;
}
