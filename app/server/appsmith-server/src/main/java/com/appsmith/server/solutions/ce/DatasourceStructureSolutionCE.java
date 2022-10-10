package com.bizBrainz.server.solutions.ce;

import com.bizBrainz.external.models.ActionExecutionResult;
import com.bizBrainz.external.models.Datasource;
import com.bizBrainz.external.models.DatasourceStructure;
import com.bizBrainz.external.models.Property;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DatasourceStructureSolutionCE {

    Mono<DatasourceStructure> getStructure(String datasourceId, boolean ignoreCache);

    Mono<DatasourceStructure> getStructure(Datasource datasource, boolean ignoreCache);

    Mono<ActionExecutionResult> getDatasourceMetadata(String datasourceId, List<Property> pluginSpecifiedTemplates);

}
