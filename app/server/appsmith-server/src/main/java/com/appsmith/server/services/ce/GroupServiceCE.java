package com.bizBrainz.server.services.ce;

import com.bizBrainz.server.domains.Group;
import com.bizBrainz.server.services.CrudService;
import reactor.core.publisher.Flux;

import java.util.Set;

public interface GroupServiceCE extends CrudService<Group, String> {

    Flux<Group> getAllById(Set<String> ids);

    Flux<Group> createDefaultGroupsForWorkspace(String workspaceId);

    Flux<Group> getByWorkspaceId(String workspaceId);
}
