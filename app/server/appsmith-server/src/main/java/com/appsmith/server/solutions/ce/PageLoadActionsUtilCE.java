package com.bizBrainz.server.solutions.ce;

import com.bizBrainz.server.domains.ActionDependencyEdge;
import com.bizBrainz.server.dtos.ActionDTO;
import com.bizBrainz.server.dtos.DslActionDTO;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PageLoadActionsUtilCE {

    Mono<List<Set<DslActionDTO>>> findAllOnLoadActions(String pageId,
                                                       Set<String> widgetNames,
                                                       Set<ActionDependencyEdge> edges,
                                                       Map<String, Set<String>> widgetDynamicBindingsMap,
                                                       List<ActionDTO> flatPageLoadActions,
                                                       Set<String> actionsUsedInDSL);

}
