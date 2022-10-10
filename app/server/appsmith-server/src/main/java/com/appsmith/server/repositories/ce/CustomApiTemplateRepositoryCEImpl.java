package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.external.models.ApiTemplate;
import com.bizBrainz.server.repositories.BaseBizbrainzRepositoryImpl;
import com.bizBrainz.server.repositories.CacheableRepositoryHelper;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.convert.MongoConverter;

public class CustomApiTemplateRepositoryCEImpl extends BaseBizbrainzRepositoryImpl<ApiTemplate>
        implements CustomApiTemplateRepositoryCE {

    public CustomApiTemplateRepositoryCEImpl(ReactiveMongoOperations mongoOperations, MongoConverter mongoConverter, CacheableRepositoryHelper cacheableRepositoryHelper) {
        super(mongoOperations, mongoConverter, cacheableRepositoryHelper);
    }
}
