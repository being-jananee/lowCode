package com.bizBrainz.server.services.ce;

import com.bizBrainz.external.models.Provider;
import com.bizBrainz.server.services.CrudService;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProviderServiceCE extends CrudService<Provider, String> {

    public Mono<List<String>> getAllCategories();

}
