package com.bizBrainz.server.services.ce;

import com.bizBrainz.server.domains.Collection;
import com.bizBrainz.server.domains.NewAction;
import com.bizBrainz.server.dtos.ActionDTO;
import com.bizBrainz.server.services.CrudService;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CollectionServiceCE extends CrudService<Collection, String> {

    Mono<Collection> findById(String id);

    Mono<Collection> addActionsToCollection(Collection collection, List<NewAction> actions);

    Mono<ActionDTO> addSingleActionToCollection(String collectionId, ActionDTO action);

    Mono<NewAction> removeSingleActionFromCollection(String collectionId, Mono<NewAction> action);
}
