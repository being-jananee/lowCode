package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.CustomPluginRepositoryCEImpl;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

@Component
public class CustomPluginRepositoryImpl extends CustomPluginRepositoryCEImpl implements CustomPluginRepository {

    public CustomPluginRepositoryImpl(ReactiveMongoOperations mongoOperations, MongoConverter mongoConverter, CacheableRepositoryHelper cacheableRepositoryHelper) {
        super(mongoOperations, mongoConverter, cacheableRepositoryHelper);
    }
}
