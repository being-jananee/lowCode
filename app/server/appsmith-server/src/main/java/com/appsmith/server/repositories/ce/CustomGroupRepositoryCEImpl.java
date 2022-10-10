package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.Group;
import com.bizBrainz.server.domains.QGroup;
import com.bizBrainz.server.repositories.BaseBizbrainzRepositoryImpl;
import com.bizBrainz.server.repositories.CacheableRepositoryHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Slf4j
public class CustomGroupRepositoryCEImpl extends BaseBizbrainzRepositoryImpl<Group>
        implements CustomGroupRepositoryCE {

    public CustomGroupRepositoryCEImpl(ReactiveMongoOperations mongoOperations, MongoConverter mongoConverter, CacheableRepositoryHelper cacheableRepositoryHelper) {
        super(mongoOperations, mongoConverter, cacheableRepositoryHelper);
    }

    @Override
    public Flux<Group> getAllByWorkspaceId(String workspaceId) {
        Criteria workspaceIdCriteria = where(fieldName(QGroup.group.workspaceId)).is(workspaceId);

        return queryAll(List.of(workspaceIdCriteria), null);
    }
}
