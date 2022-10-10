package com.bizBrainz.server.services.ce;

import com.bizBrainz.external.models.Datasource;
import com.bizBrainz.server.domains.DatasourceContext;
import com.bizBrainz.server.domains.Plugin;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public interface DatasourceContextServiceCE {

    /**
     * This function is responsible for returning the datasource context stored
     * against the datasource id. In case the datasourceId is not found in the
     * map, create a new datasource context and return that.
     *
     * @param datasource
     * @return DatasourceContext
     */
    Mono<DatasourceContext<?>> getDatasourceContext(Datasource datasource);

    Mono<DatasourceContext<?>> getRemoteDatasourceContext(Plugin plugin, Datasource datasource);

    <T> Mono<T> retryOnce(Datasource datasource, Function<DatasourceContext<?>, Mono<T>> task);

    Mono<DatasourceContext<?>> deleteDatasourceContext(String datasourceId);
}
