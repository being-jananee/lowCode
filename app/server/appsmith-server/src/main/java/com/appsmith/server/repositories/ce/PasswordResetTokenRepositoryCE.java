package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.PasswordResetToken;
import com.bizBrainz.server.repositories.BaseRepository;
import reactor.core.publisher.Mono;

public interface PasswordResetTokenRepositoryCE extends BaseRepository<PasswordResetToken, String> {

    Mono<PasswordResetToken> findByEmail(String email);

}
