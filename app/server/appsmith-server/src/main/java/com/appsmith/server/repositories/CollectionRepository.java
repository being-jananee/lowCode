package com.bizBrainz.server.repositories;

import com.bizBrainz.server.domains.Collection;
import com.bizBrainz.server.repositories.ce.CollectionRepositoryCE;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CollectionRepository extends CollectionRepositoryCE, CustomCollectionRepository {

}
