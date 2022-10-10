package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.acl.AclPermission;
import com.bizBrainz.server.constants.FieldName;
import com.bizBrainz.server.domains.PermissionGroup;
import com.bizBrainz.server.domains.QPermissionGroup;
import com.bizBrainz.server.exceptions.BizbrainzError;
import com.bizBrainz.server.exceptions.BizbrainzException;
import com.bizBrainz.server.repositories.BaseBizbrainzRepositoryImpl;
import com.bizBrainz.server.repositories.CacheableRepositoryHelper;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class CustomPermissionGroupRepositoryCEImpl extends BaseBizbrainzRepositoryImpl<PermissionGroup>
        implements CustomPermissionGroupRepositoryCE {

    public CustomPermissionGroupRepositoryCEImpl(ReactiveMongoOperations mongoOperations, MongoConverter mongoConverter, CacheableRepositoryHelper cacheableRepositoryHelper) {
        super(mongoOperations, mongoConverter, cacheableRepositoryHelper);
    }

    @Override
    public Flux<PermissionGroup> findAllByAssignedToUserIdAndDefaultWorkspaceId(String userId, String workspaceId,
            AclPermission permission) {
        Criteria assignedToUserIdCriteria = where(fieldName(QPermissionGroup.permissionGroup.assignedToUserIds)).in(userId);
        Criteria defaultWorkspaceIdCriteria = where(fieldName(QPermissionGroup.permissionGroup.defaultWorkspaceId)).is(workspaceId);
        return queryAll(List.of(assignedToUserIdCriteria, defaultWorkspaceIdCriteria), permission);
    }

    @Override
    public Mono<UpdateResult> updateById(String id, Update updateObj) {
        if (id == null) {
            return Mono.error(new BizbrainzException(BizbrainzError.INVALID_PARAMETER, FieldName.ID));
        }
        Query query = new Query(Criteria.where("id").is(id));
        return mongoOperations.updateFirst(query, updateObj, this.genericDomain);
    }

    @Override
    public Flux<PermissionGroup> findByDefaultWorkspaceId(String workspaceId, AclPermission permission) {
        Criteria defaultWorkspaceIdCriteria = where(fieldName(QPermissionGroup.permissionGroup.defaultWorkspaceId)).is(workspaceId);
        return queryAll(List.of(defaultWorkspaceIdCriteria), permission);
    }

    @Override
    public Flux<PermissionGroup> findByDefaultWorkspaceIds(Set<String> workspaceIds, AclPermission permission) {
        Criteria defaultWorkspaceIdCriteria = where(fieldName(QPermissionGroup.permissionGroup.defaultWorkspaceId)).in(workspaceIds);
        return queryAll(List.of(defaultWorkspaceIdCriteria), permission);
    }

    @Override
    public Mono<Void> evictPermissionGroupsUser(String email, String tenantId) {
        return cacheableRepositoryHelper.evictPermissionGroupsUser(email, tenantId);
    }
}
