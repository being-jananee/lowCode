package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.NewPage;
import com.bizBrainz.server.repositories.BaseRepository;
import com.bizBrainz.server.repositories.CustomNewPageRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NewPageRepositoryCE extends BaseRepository<NewPage, String>, CustomNewPageRepository {

    Flux<NewPage> findByApplicationId(String applicationId);

    Mono<Long> countByDeletedAtNull();

}
