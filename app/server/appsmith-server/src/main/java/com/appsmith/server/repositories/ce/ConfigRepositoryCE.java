package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.Config;
import com.bizBrainz.server.repositories.BaseRepository;
import com.bizBrainz.server.repositories.CustomConfigRepository;
import reactor.core.publisher.Mono;

public interface ConfigRepositoryCE extends BaseRepository<Config, String>, CustomConfigRepository {

    Mono<Config> findByName(String name);
}
