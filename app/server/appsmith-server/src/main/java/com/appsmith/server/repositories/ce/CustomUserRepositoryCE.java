package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.acl.AclPermission;
import com.bizBrainz.server.domains.User;
import com.bizBrainz.server.repositories.BizbrainzRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface CustomUserRepositoryCE extends BizbrainzRepository<User> {

    Mono<User> findByEmail(String email, AclPermission aclPermission);

    Flux<User> findAllByEmails(Set<String> emails);

    Mono<User> findByCaseInsensitiveEmail(String email);

    Mono<User> findByEmailAndTenantId(String email, String tenantId);

    Mono<Boolean> isUsersEmpty();

}
