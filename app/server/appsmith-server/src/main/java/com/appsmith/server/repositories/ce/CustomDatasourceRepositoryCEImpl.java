package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.external.models.Datasource;
import com.bizBrainz.external.models.DatasourceStructure;
import com.bizBrainz.external.models.QDatasource;
import com.bizBrainz.server.acl.AclPermission;
import com.bizBrainz.server.repositories.BaseBizbrainzRepositoryImpl;
import com.bizBrainz.server.repositories.CacheableRepositoryHelper;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class CustomDatasourceRepositoryCEImpl extends BaseBizbrainzRepositoryImpl<Datasource> implements CustomDatasourceRepositoryCE {

    public CustomDatasourceRepositoryCEImpl(ReactiveMongoOperations mongoOperations, MongoConverter mongoConverter, CacheableRepositoryHelper cacheableRepositoryHelper) {
        super(mongoOperations, mongoConverter, cacheableRepositoryHelper);
    }

    @Override
    public Flux<Datasource> findAllByWorkspaceId(String workspaceId, AclPermission permission) {
        Criteria workspaceIdCriteria = where(fieldName(QDatasource.datasource.workspaceId)).is(workspaceId);
        return queryAll(List.of(workspaceIdCriteria), permission, Sort.by(fieldName(QDatasource.datasource.name)));
    }

    @Override
    public Mono<Datasource> findByNameAndWorkspaceId(String name, String workspaceId, AclPermission aclPermission) {
        Criteria nameCriteria = where(fieldName(QDatasource.datasource.name)).is(name);
        Criteria workspaceIdCriteria = where(fieldName(QDatasource.datasource.workspaceId)).is(workspaceId);
        return queryOne(List.of(nameCriteria, workspaceIdCriteria), aclPermission);
    }

    @Override
    public Flux<Datasource> findAllByIds(Set<String> ids, AclPermission permission) {
        Criteria idcriteria = where(fieldName(QDatasource.datasource.id)).in(ids);
        return queryAll(List.of(idcriteria), permission);
    }

    public Mono<UpdateResult> saveStructure(String datasourceId, DatasourceStructure structure) {
        return mongoOperations.updateFirst(
                query(where(fieldName(QDatasource.datasource.id)).is(datasourceId)),
                Update.update(fieldName(QDatasource.datasource.structure), structure),
                Datasource.class
        );
    }
}
