package com.bizBrainz.server.services.ce;

import com.bizBrainz.server.dtos.ActionDTO;
import reactor.core.publisher.Mono;

public abstract class BaseApiImporterCE implements ApiImporterCE {

    public abstract Mono<ActionDTO> importAction(Object input, String pageId, String name, String workspaceId, String branchName);

}
