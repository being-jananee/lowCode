package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.external.models.Datasource;
import com.bizBrainz.external.models.DatasourceStructure;
import com.bizBrainz.server.acl.AclPermission;
import com.bizBrainz.server.repositories.BizbrainzRepository;
import com.mongodb.client.result.UpdateResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface CustomDatasourceRepositoryCE extends BizbrainzRepository<Datasource> {

    Flux<Datasource> findAllByWorkspaceId(String workspaceId, AclPermission permission);

    Mono<Datasource> findByNameAndWorkspaceId(String name, String workspaceId, AclPermission aclPermission);

    Mono<Datasource> findById(String id, AclPermission aclPermission);

    Flux<Datasource> findAllByIds(Set<String> ids, AclPermission permission);

    Mono<UpdateResult> saveStructure(String datasourceId, DatasourceStructure structure);

}
