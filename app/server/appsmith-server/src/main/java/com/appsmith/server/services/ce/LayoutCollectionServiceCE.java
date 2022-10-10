package com.bizBrainz.server.services.ce;

import com.bizBrainz.server.dtos.ActionCollectionDTO;
import com.bizBrainz.server.dtos.ActionCollectionMoveDTO;
import com.bizBrainz.server.dtos.LayoutDTO;
import com.bizBrainz.server.dtos.RefactorActionCollectionNameDTO;
import com.bizBrainz.server.dtos.RefactorActionNameInCollectionDTO;
import reactor.core.publisher.Mono;

public interface LayoutCollectionServiceCE {

    Mono<ActionCollectionDTO> createCollection(ActionCollectionDTO collection);

    Mono<ActionCollectionDTO> createCollection(ActionCollectionDTO collection, String branchName);

    Mono<LayoutDTO> refactorCollectionName(RefactorActionCollectionNameDTO refactorActionCollectionNameDTO, String branchName);

    Mono<ActionCollectionDTO> moveCollection(ActionCollectionMoveDTO actionCollectionMoveDTO);

    Mono<ActionCollectionDTO> moveCollection(ActionCollectionMoveDTO actionCollectionMoveDTO, String branchName);

    Mono<ActionCollectionDTO> updateUnpublishedActionCollection(String id, ActionCollectionDTO actionCollectionDTO, String branchName);

    Mono<LayoutDTO> refactorAction(RefactorActionNameInCollectionDTO refactorActionNameInCollectionDTO);

    Mono<LayoutDTO> refactorAction(RefactorActionNameInCollectionDTO refactorActionNameInCollectionDTO, String branchName);

}
