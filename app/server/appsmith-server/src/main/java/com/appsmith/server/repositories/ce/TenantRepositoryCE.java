package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.Tenant;
import com.bizBrainz.server.repositories.BaseRepository;
import reactor.core.publisher.Mono;

public interface TenantRepositoryCE extends BaseRepository<Tenant, String> {

    Mono<Tenant> findBySlug(String slug);

}
