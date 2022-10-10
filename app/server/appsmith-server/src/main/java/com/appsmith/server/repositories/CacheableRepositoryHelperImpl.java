package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.CacheableRepositoryHelperCEImpl;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Component;

@Component
public class CacheableRepositoryHelperImpl extends CacheableRepositoryHelperCEImpl implements CacheableRepositoryHelper{

    public CacheableRepositoryHelperImpl(ReactiveMongoOperations mongoOperations) {
        super(mongoOperations);
    }
}
