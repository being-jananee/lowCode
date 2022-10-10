package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.acl.AclPermission;
import com.bizBrainz.server.domains.Config;
import com.bizBrainz.server.domains.User;
import com.bizBrainz.server.repositories.BizbrainzRepository;
import reactor.core.publisher.Mono;

public interface CustomConfigRepositoryCE extends BizbrainzRepository<Config> {
    Mono<Config> findByName(String name, AclPermission permission);

    Mono<Config> findByNameAsUser(String name, User user, AclPermission permission);
}
