package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.CustomUserDataRepositoryCEImpl;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDataRepositoryImpl extends CustomUserDataRepositoryCEImpl implements CustomUserDataRepository {

    public CustomUserDataRepositoryImpl(ReactiveMongoOperations mongoOperations, MongoConverter mongoConverter, CacheableRepositoryHelper cacheableRepositoryHelper) {
        super(mongoOperations, mongoConverter, cacheableRepositoryHelper);
    }

}
