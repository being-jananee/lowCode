package com.bizBrainz.server.solutions.ce;

import com.bizBrainz.server.dtos.UserHomepageDTO;
import reactor.core.publisher.Mono;

public interface ApplicationFetcherCE {

    Mono<UserHomepageDTO> getAllApplications();

}
