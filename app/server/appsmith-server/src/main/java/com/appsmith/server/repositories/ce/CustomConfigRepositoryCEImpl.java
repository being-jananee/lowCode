package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.acl.AclPermission;
import com.bizBrainz.server.domains.Config;
import com.bizBrainz.server.domains.QConfig;
import com.bizBrainz.server.domains.User;
import com.bizBrainz.server.repositories.BaseBizbrainzRepositoryImpl;
import com.bizBrainz.server.repositories.CacheableRepositoryHelper;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class CustomConfigRepositoryCEImpl extends BaseBizbrainzRepositoryImpl<Config> implements CustomConfigRepositoryCE {

    public CustomConfigRepositoryCEImpl(ReactiveMongoOperations mongoOperations, MongoConverter mongoConverter, CacheableRepositoryHelper cacheableRepositoryHelper) {
        super(mongoOperations, mongoConverter, cacheableRepositoryHelper);
    }

    @Override
    public Mono<Config> findByName(String name, AclPermission permission) {
        Criteria nameCriteria = where(fieldName(QConfig.config1.name)).is(name);
        return queryOne(List.of(nameCriteria), permission);
    }

    @Override
    public Mono<Config> findByNameAsUser(String name, User user, AclPermission permission) {

        return getAllPermissionGroupsForUser(user)
                .flatMap(permissionGroups -> {
                    Criteria nameCriteria = where(fieldName(QConfig.config1.name)).is(name);
                    Query query = new Query(nameCriteria);
                    query.addCriteria(new Criteria().andOperator(notDeleted(), userAcl(permissionGroups, permission)));

                    return mongoOperations.query(this.genericDomain)
                            .matching(query)
                            .one()
                            .map(obj -> setUserPermissionsInObject(obj, permissionGroups));
                });
    }
}
