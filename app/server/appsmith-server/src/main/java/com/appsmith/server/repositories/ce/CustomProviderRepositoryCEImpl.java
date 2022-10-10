package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.external.models.Provider;
import com.bizBrainz.server.repositories.BaseBizbrainzRepositoryImpl;
import com.bizBrainz.server.repositories.CacheableRepositoryHelper;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.convert.MongoConverter;

public class CustomProviderRepositoryCEImpl extends BaseBizbrainzRepositoryImpl<Provider> implements CustomProviderRepositoryCE {

    public CustomProviderRepositoryCEImpl(ReactiveMongoOperations mongoOperations, MongoConverter mongoConverter, CacheableRepositoryHelper cacheableRepositoryHelper) {
        super(mongoOperations, mongoConverter, cacheableRepositoryHelper);
    }
}
