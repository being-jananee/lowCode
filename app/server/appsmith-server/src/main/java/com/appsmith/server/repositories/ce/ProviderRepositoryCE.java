package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.external.models.Provider;
import com.bizBrainz.server.repositories.BaseRepository;
import com.bizBrainz.server.repositories.CustomProviderRepository;
import reactor.core.publisher.Flux;

public interface ProviderRepositoryCE extends BaseRepository<Provider, String>, CustomProviderRepository {
    Flux<Provider> findByName(String name);
}
