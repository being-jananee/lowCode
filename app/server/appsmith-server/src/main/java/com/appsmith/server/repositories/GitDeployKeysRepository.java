package com.bizBrainz.server.repositories;

import com.bizBrainz.server.domains.GitDeployKeys;
import reactor.core.publisher.Mono;

public interface GitDeployKeysRepository extends BaseRepository<GitDeployKeys, String>{
    Mono<GitDeployKeys> findByEmail(String email);
}
