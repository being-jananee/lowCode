package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.User;
import com.bizBrainz.server.repositories.BaseRepository;
import com.bizBrainz.server.repositories.CustomUserRepository;
import reactor.core.publisher.Mono;

public interface UserRepositoryCE extends BaseRepository<User, String>, CustomUserRepository {

    Mono<User> findByEmail(String email);

    Mono<User> findByCaseInsensitiveEmail(String email);

    Mono<Long> countByDeletedAtNull();

    Mono<User> findByEmailAndTenantId(String email, String tenantId);

}
