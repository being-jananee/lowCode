package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.Plugin;
import com.bizBrainz.server.domains.PluginType;
import com.bizBrainz.server.repositories.BaseRepository;
import com.bizBrainz.server.repositories.CustomPluginRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PluginRepositoryCE extends BaseRepository<Plugin, String>, CustomPluginRepository {
    Mono<Plugin> findByName(String name);

    Mono<Plugin> findByPackageName(String packageName);

    Mono<Plugin> findById(String id);

    Flux<Plugin> findByDefaultInstall(Boolean isDefaultInstall);

    Flux<Plugin> findByType(PluginType pluginType);
}
