package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.acl.AclPermission;
import com.bizBrainz.server.domains.PermissionGroup;
import com.bizBrainz.server.repositories.BaseRepository;
import com.bizBrainz.server.repositories.CustomPermissionGroupRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface PermissionGroupRepositoryCE extends BaseRepository<PermissionGroup, String>, CustomPermissionGroupRepository {

    Flux<PermissionGroup> findAllById(Set<String> ids);

    Mono<PermissionGroup> findById(String id, AclPermission permission);

    Flux<PermissionGroup> findByAssignedToUserIdsIn(String userId);

    Flux<PermissionGroup> findByDefaultWorkspaceId(String defaultWorkspaceId);

}
