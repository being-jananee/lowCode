package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.Group;
import com.bizBrainz.server.repositories.BizbrainzRepository;
import reactor.core.publisher.Flux;

public interface CustomGroupRepositoryCE extends BizbrainzRepository<Group> {
    Flux<Group> getAllByWorkspaceId(String workspaceId);
}
