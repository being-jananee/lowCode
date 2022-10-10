package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.acl.AclPermission;
import com.bizBrainz.server.domains.Workspace;
import com.bizBrainz.server.repositories.BizbrainzRepository;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface CustomWorkspaceRepositoryCE extends BizbrainzRepository<Workspace> {

    Mono<Workspace> findByName(String name, AclPermission aclPermission);

    Flux<Workspace> findByIdsIn(Set<String> workspaceIds, String tenantId, AclPermission aclPermission, Sort sort);

    Mono<Void> updateUserRoleNames(String userId, String userName);

    Flux<Workspace> findAllWorkspaces();

    Flux<Workspace> findAll(AclPermission permission);
}
