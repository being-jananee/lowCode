package com.bizBrainz.server.services.ce;

import com.bizBrainz.server.dtos.ActionDTO;
import com.bizBrainz.server.dtos.AddItemToPageDTO;
import com.bizBrainz.server.dtos.ItemDTO;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemServiceCE {

    Flux<ItemDTO> get(MultiValueMap<String, String> params);

    Mono<ActionDTO> addItemToPage(AddItemToPageDTO addItemToPageDTO);

}
