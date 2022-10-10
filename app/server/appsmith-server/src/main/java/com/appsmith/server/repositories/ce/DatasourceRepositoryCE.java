package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.external.models.Datasource;
import com.bizBrainz.server.repositories.BaseRepository;
import com.bizBrainz.server.repositories.CustomDatasourceRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DatasourceRepositoryCE extends BaseRepository<Datasource, String>, CustomDatasourceRepository {

    Flux<Datasource> findByIdIn(List<String> ids);

    Flux<Datasource> findAllByWorkspaceId(String workspaceId);

    Mono<Long> countByDeletedAtNull();

}
