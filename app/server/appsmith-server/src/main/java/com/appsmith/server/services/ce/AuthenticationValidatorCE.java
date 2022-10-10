package com.bizBrainz.server.services.ce;

import com.bizBrainz.external.models.Datasource;
import reactor.core.publisher.Mono;


public interface AuthenticationValidatorCE {

    Mono<Datasource> validateAuthentication(Datasource datasource);

}
