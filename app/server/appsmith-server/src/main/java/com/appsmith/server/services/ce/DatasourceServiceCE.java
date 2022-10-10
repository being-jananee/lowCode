package com.bizBrainz.server.services.ce;

import com.bizBrainz.external.models.Datasource;
import com.bizBrainz.external.models.DatasourceTestResult;
import com.bizBrainz.server.acl.AclPermission;
import com.bizBrainz.server.dtos.ActionDTO;
import com.bizBrainz.server.services.CrudService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

public interface DatasourceServiceCE extends CrudService<Datasource, String> {

    Mono<DatasourceTestResult> testDatasource(Datasource datasource);

    Mono<Datasource> findByNameAndWorkspaceId(String name, String workspaceId, AclPermission permission);

    Mono<Datasource> findById(String id, AclPermission aclPermission);

    Mono<Datasource> findById(String id);

    Set<String> extractKeysFromDatasource(Datasource datasource);

    Mono<Datasource> validateDatasource(Datasource datasource);

    Mono<Datasource> save(Datasource datasource);

    Flux<Datasource> findAllByWorkspaceId(String workspaceId, AclPermission readDatasources);

    Flux<Datasource> saveAll(List<Datasource> datasourceList);

    Mono<Datasource> populateHintMessages(Datasource datasource);

    Mono<Datasource> update(String datasourceId, Datasource datasource, Boolean isServerRefreshedUpdate);

    Mono<Datasource> getValidDatasourceFromActionMono(ActionDTO actionDTO, AclPermission aclPermission);

}
