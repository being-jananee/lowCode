package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.CustomActionRepositoryCEImpl;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

@Component
public class CustomActionRepositoryImpl extends CustomActionRepositoryCEImpl implements CustomActionRepository {

    public CustomActionRepositoryImpl(ReactiveMongoOperations mongoOperations, MongoConverter mongoConverter, CacheableRepositoryHelper cacheableRepositoryHelper) {
        super(mongoOperations, mongoConverter, cacheableRepositoryHelper);
    }

}
