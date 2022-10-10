package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.QUserData;
import com.bizBrainz.server.domains.UserData;
import com.bizBrainz.server.repositories.BaseBizbrainzRepositoryImpl;
import com.bizBrainz.server.repositories.CacheableRepositoryHelper;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class CustomUserDataRepositoryCEImpl extends BaseBizbrainzRepositoryImpl<UserData> implements CustomUserDataRepositoryCE {

    public CustomUserDataRepositoryCEImpl(ReactiveMongoOperations mongoOperations, MongoConverter mongoConverter, CacheableRepositoryHelper cacheableRepositoryHelper) {
        super(mongoOperations, mongoConverter, cacheableRepositoryHelper);
    }

    @Override
    public Mono<UpdateResult> saveReleaseNotesViewedVersion(String userId, String version) {
        return mongoOperations
                .upsert(
                        query(where(fieldName(QUserData.userData.userId)).is(userId)),
                        Update
                                .update(fieldName(QUserData.userData.releaseNotesViewedVersion), version)
                                .setOnInsert(fieldName(QUserData.userData.userId), userId),
                        UserData.class
                );
    }

    @Override
    public Mono<UpdateResult> removeIdFromRecentlyUsedList(String userId, String workspaceId, List<String> applicationIds) {
        Update update = new Update().pull(fieldName(QUserData.userData.recentlyUsedWorkspaceIds), workspaceId);
        if(!CollectionUtils.isEmpty(applicationIds)) {
            update = update.pullAll(fieldName(QUserData.userData.recentlyUsedAppIds), applicationIds.toArray());
        }
        return mongoOperations.updateFirst(
                query(where(fieldName(QUserData.userData.userId)).is(userId)), update, UserData.class
        );
    }
}
