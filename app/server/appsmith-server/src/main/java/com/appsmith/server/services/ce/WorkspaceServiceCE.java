package com.bizBrainz.server.services.ce;

import com.bizBrainz.external.exceptions.BaseException;
import com.bizBrainz.server.acl.AclPermission;
import com.bizBrainz.server.domains.User;
import com.bizBrainz.server.domains.Workspace;
import com.bizBrainz.server.dtos.PermissionGroupInfoDTO;
import com.bizBrainz.server.services.CrudService;
import org.springframework.http.codec.multipart.Part;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

public interface WorkspaceServiceCE extends CrudService<Workspace, String> {

    String getDefaultNameForGroupInWorkspace(String prefix, String workspaceName);

    Mono<Workspace> create(Workspace workspace);

    Mono<Workspace> createDefault(Workspace workspace, User user);

    Mono<Workspace> create(Workspace workspace, User user);

    Mono<Workspace> findById(String id, AclPermission permission);

    Mono<Workspace> save(Workspace workspace);

    Mono<Workspace> findByIdAndPluginsPluginId(String workspaceId, String pluginId);

    Flux<Workspace> findByIdsIn(Set<String> ids, String tenantId, AclPermission permission);

    Flux<Workspace> getAll(AclPermission permission);

    Mono<List<PermissionGroupInfoDTO>> getPermissionGroupsForWorkspace(String workspaceId);

    Mono<Workspace> uploadLogo(String workspaceId, Part filePart);

    Mono<Workspace> deleteLogo(String workspaceId);

    Flux<Workspace> getAll();

    Mono<Workspace> archiveById(String s);
}
