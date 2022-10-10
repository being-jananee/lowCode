package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.Workspace;
import com.bizBrainz.server.repositories.BaseRepository;
import com.bizBrainz.server.repositories.CustomWorkspaceRepository;
import reactor.core.publisher.Mono;

public interface WorkspaceRepositoryCE extends BaseRepository<Workspace, String>, CustomWorkspaceRepository {

    Mono<Workspace> findBySlug(String slug);

    Mono<Workspace> findByIdAndPluginsPluginId(String workspaceId, String pluginId);

    Mono<Workspace> findByName(String name);

    Mono<Void> updateUserRoleNames(String userId, String userName);

    Mono<Long> countByDeletedAtNull();

}
