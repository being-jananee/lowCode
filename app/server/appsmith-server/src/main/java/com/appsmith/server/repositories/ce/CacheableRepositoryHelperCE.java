package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.User;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface CacheableRepositoryHelperCE {

    Mono<Set<String>> getPermissionGroupsOfUser(User user);

    Mono<Set<String>> getPermissionGroupsOfAnonymousUser();

    Mono<Void> evictPermissionGroupsUser(String email, String tenantId);

    Mono<User> getAnonymousUser(String tenantId);

    Mono<User> getAnonymousUser();

    Mono<String> getDefaultTenantId();
}
