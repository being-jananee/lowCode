package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.Plugin;
import com.bizBrainz.server.repositories.BizbrainzRepository;
import reactor.core.publisher.Flux;

public interface CustomPluginRepositoryCE extends BizbrainzRepository<Plugin> {
    Flux<Plugin> findDefaultPluginIcons();
}
