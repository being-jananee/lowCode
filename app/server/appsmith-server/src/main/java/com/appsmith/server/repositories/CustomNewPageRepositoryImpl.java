package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.CustomNewPageRepositoryCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomNewPageRepositoryImpl extends CustomNewPageRepositoryCEImpl
        implements CustomNewPageRepository {

    public CustomNewPageRepositoryImpl(ReactiveMongoOperations mongoOperations, MongoConverter mongoConverter, CacheableRepositoryHelper cacheableRepositoryHelper) {
        super(mongoOperations, mongoConverter, cacheableRepositoryHelper);
    }

}
