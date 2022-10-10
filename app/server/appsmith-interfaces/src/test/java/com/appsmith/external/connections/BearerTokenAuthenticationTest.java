package com.bizBrainz.external.connections;

import com.bizBrainz.external.helpers.restApiUtils.connections.BearerTokenAuthentication;
import com.bizBrainz.external.models.BearerTokenAuth;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BearerTokenAuthenticationTest {

    @Test
    public void testCreateMethod() {
        String bearerToken = "key";
        BearerTokenAuth bearerTokenAuthDTO = new BearerTokenAuth(bearerToken);
        Mono<BearerTokenAuthentication> connectionMono = BearerTokenAuthentication.create(bearerTokenAuthDTO);
        StepVerifier.create(connectionMono)
                .assertNext(connection -> {
                    assertThat(connection).isNotNull();
                    assertEquals(bearerToken, connection.getBearerToken());
                })
                .verifyComplete();
    }
}