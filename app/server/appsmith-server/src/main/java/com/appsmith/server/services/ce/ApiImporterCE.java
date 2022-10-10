package com.bizBrainz.server.services.ce;

import com.bizBrainz.server.dtos.ActionDTO;
import reactor.core.publisher.Mono;

public interface ApiImporterCE {

    Mono<ActionDTO> importAction(Object input, String pageId, String name, String workspaceId, String branchName);

}
