package com.bizBrainz.server.services.ce;

import com.bizBrainz.external.models.AuthenticationDTO;
import com.bizBrainz.external.models.Datasource;
import com.bizBrainz.external.models.OAuth2;
import com.bizBrainz.server.solutions.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationValidatorCEImpl implements AuthenticationValidatorCE {

    private final AuthenticationService authenticationService;

    public Mono<Datasource> validateAuthentication(Datasource datasource) {
        if (datasource.getDatasourceConfiguration() == null || datasource.getDatasourceConfiguration().getAuthentication() == null) {
            return Mono.just(datasource);
        }
        AuthenticationDTO authentication = datasource.getDatasourceConfiguration().getAuthentication();
        return authentication.hasExpired()
                .filter(expired -> expired)
                .flatMap(expired -> {
                    if (authentication instanceof OAuth2) {
                        return authenticationService.refreshAuthentication(datasource);
                    }
                    return Mono.just(datasource);
                })
                .switchIfEmpty(Mono.just(datasource));
    }
}
