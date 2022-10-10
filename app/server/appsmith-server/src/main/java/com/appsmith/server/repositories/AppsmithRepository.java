package com.bizBrainz.server.repositories;

import com.bizBrainz.external.models.Policy;
import com.bizBrainz.server.acl.AclPermission;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

public interface BizbrainzRepository<T> {

    Mono<T> findById(String id, AclPermission permission);

    Mono<T> updateById(String id, T resource, AclPermission permission);

    Flux<T> queryAll(List<Criteria> criterias, AclPermission permission);

    Flux<T> queryAll(List<Criteria> criterias, AclPermission permission, Sort sort);

    Flux<T> queryAll(List<Criteria> criterias, List<String> includeFields, AclPermission permission, Sort sort);

    T setUserPermissionsInObject(T obj, Set<String> permissionGroups);

    Mono<T> findByGitSyncIdAndDefaultApplicationId(String defaultApplicationId, String gitSyncId, AclPermission permission);

    Mono<Boolean> isPermissionPresentForUser(Set<Policy> policies, String permission, String username);
}
