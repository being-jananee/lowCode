package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.CustomProviderRepositoryCEImpl;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

@Component
public class CustomProviderRepositoryImpl extends CustomProviderRepositoryCEImpl implements CustomProviderRepository {

    public CustomProviderRepositoryImpl(ReactiveMongoOperations mongoOperations, MongoConverter mongoConverter, CacheableRepositoryHelper cacheableRepositoryHelper) {
        super(mongoOperations, mongoConverter, cacheableRepositoryHelper);
    }
}
