package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.NewAction;
import com.bizBrainz.server.repositories.BaseRepository;
import com.bizBrainz.server.repositories.CustomNewActionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NewActionRepositoryCE extends BaseRepository<NewAction, String>, CustomNewActionRepository {

    Flux<NewAction> findByApplicationId(String applicationId);

    Mono<Long> countByDeletedAtNull();

}
