package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.Collection;
import com.bizBrainz.server.repositories.BaseBizbrainzRepositoryImpl;
import com.bizBrainz.server.repositories.CacheableRepositoryHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.convert.MongoConverter;

public class CustomCollectionRepositoryCEImpl extends BaseBizbrainzRepositoryImpl<Collection> implements CustomCollectionRepositoryCE {

    @Autowired
    public CustomCollectionRepositoryCEImpl(ReactiveMongoOperations mongoOperations, MongoConverter mongoConverter, CacheableRepositoryHelper cacheableRepositoryHelper) {
        super(mongoOperations, mongoConverter, cacheableRepositoryHelper);
    }
}
