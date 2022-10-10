package com.bizBrainz.server.solutions.ce;

import com.bizBrainz.server.domains.Application;
import reactor.core.publisher.Mono;

public interface ApplicationForkingServiceCE {

    Mono<Application> forkApplicationToWorkspace(String srcApplicationId, String targetWorkspaceId);

    Mono<Application> forkApplicationToWorkspace(String srcApplicationId,
                                                    String targetWorkspaceId,
                                                    String branchName);

}
