package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.Plugin;
import com.bizBrainz.server.domains.QPlugin;
import com.bizBrainz.server.repositories.BaseBizbrainzRepositoryImpl;
import com.bizBrainz.server.repositories.CacheableRepositoryHelper;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import reactor.core.publisher.Flux;

import java.util.List;

public class CustomPluginRepositoryCEImpl extends BaseBizbrainzRepositoryImpl<Plugin> implements CustomPluginRepositoryCE {

    public CustomPluginRepositoryCEImpl(ReactiveMongoOperations mongoOperations, MongoConverter mongoConverter, CacheableRepositoryHelper cacheableRepositoryHelper) {
        super(mongoOperations, mongoConverter, cacheableRepositoryHelper);
    }

    @Override
    public Flux<Plugin> findDefaultPluginIcons() {
        Criteria criteria = Criteria.where(fieldName(QPlugin.plugin.defaultInstall)).is(Boolean.TRUE);
        List<String> projections = List.of(
                fieldName(QPlugin.plugin.name),
                fieldName(QPlugin.plugin.packageName),
                fieldName(QPlugin.plugin.iconLocation)
        );
        return this.queryAll(List.of(criteria), projections, null, null);
    }
}
