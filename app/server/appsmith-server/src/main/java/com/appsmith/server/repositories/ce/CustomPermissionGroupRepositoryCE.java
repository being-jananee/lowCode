package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.acl.AclPermission;
import com.bizBrainz.server.domains.PermissionGroup;
import com.bizBrainz.server.repositories.BizbrainzRepository;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface CustomPermissionGroupRepositoryCE extends BizbrainzRepository<PermissionGroup> {

    Flux<PermissionGroup> findAllByAssignedToUserIdAndDefaultWorkspaceId(String userId, String workspaceId, AclPermission permission);

    Mono<UpdateResult> updateById(String id, Update updateObj);

    Flux<PermissionGroup> findByDefaultWorkspaceId(String workspaceId, AclPermission permission);

    Flux<PermissionGroup> findByDefaultWorkspaceIds(Set<String> workspaceIds, AclPermission permission);

    Mono<Void> evictPermissionGroupsUser(String email, String tenantId);

}
