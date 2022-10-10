package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.ActionCollection;
import com.bizBrainz.server.repositories.BaseRepository;
import com.bizBrainz.server.repositories.CustomActionCollectionRepository;
import reactor.core.publisher.Flux;

public interface ActionCollectionRepositoryCE extends BaseRepository<ActionCollection, String>, CustomActionCollectionRepository {
    Flux<ActionCollection> findByApplicationId(String applicationId);
}
