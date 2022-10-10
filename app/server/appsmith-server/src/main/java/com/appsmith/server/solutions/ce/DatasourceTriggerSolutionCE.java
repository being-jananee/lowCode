package com.bizBrainz.server.solutions.ce;

import com.bizBrainz.external.models.TriggerRequestDTO;
import com.bizBrainz.external.models.TriggerResultDTO;
import reactor.core.publisher.Mono;


public interface DatasourceTriggerSolutionCE {

    Mono<TriggerResultDTO> trigger(String datasourceId, TriggerRequestDTO triggerRequestDTO);

}
