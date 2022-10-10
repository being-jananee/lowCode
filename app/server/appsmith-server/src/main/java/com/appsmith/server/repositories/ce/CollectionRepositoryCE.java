package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.Collection;
import com.bizBrainz.server.repositories.BaseRepository;
import com.bizBrainz.server.repositories.CustomCollectionRepository;
import reactor.core.publisher.Mono;

public interface CollectionRepositoryCE extends BaseRepository<Collection, String>, CustomCollectionRepository {
    Mono<Collection> findById(String id);
}
