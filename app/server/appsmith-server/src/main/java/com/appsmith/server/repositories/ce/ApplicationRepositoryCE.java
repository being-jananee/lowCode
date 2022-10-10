package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.Application;
import com.bizBrainz.server.repositories.BaseRepository;
import com.bizBrainz.server.repositories.CustomApplicationRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface ApplicationRepositoryCE extends BaseRepository<Application, String>, CustomApplicationRepository {

    Flux<Application> findByIdIn(List<String> ids);

    Flux<Application> findByWorkspaceId(String workspaceId);

    Flux<Application> findByClonedFromApplicationId(String clonedFromApplicationId);

    Mono<Long> countByDeletedAtNull();

    Mono<Application> findByIdAndExportWithConfiguration(String id, boolean exportWithConfiguration);

}
