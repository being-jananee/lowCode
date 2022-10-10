package com.bizBrainz.external.helpers.restApiUtils.connections;

import com.bizBrainz.external.exceptions.pluginExceptions.BizbrainzPluginError;
import com.bizBrainz.external.exceptions.pluginExceptions.BizbrainzPluginException;
import com.bizBrainz.external.models.AuthenticationDTO;
import com.bizBrainz.external.models.BasicAuth;
import com.bizBrainz.external.models.DatasourceConfiguration;
import com.bizBrainz.external.models.OAuth2;
import com.bizBrainz.external.models.ApiKeyAuth;
import com.bizBrainz.external.models.BearerTokenAuth;
import reactor.core.publisher.Mono;


public class APIConnectionFactory {

    public static Mono<APIConnection> createConnection(DatasourceConfiguration datasourceConfiguration) {
        final AuthenticationDTO authentication = datasourceConfiguration.getAuthentication();
        if (authentication instanceof OAuth2) {
            if (OAuth2.Type.CLIENT_CREDENTIALS.equals(((OAuth2) authentication).getGrantType())) {
                return Mono.from(OAuth2ClientCredentials.create(datasourceConfiguration));
            } else if (OAuth2.Type.AUTHORIZATION_CODE.equals(((OAuth2) authentication).getGrantType())) {
                if (!Boolean.TRUE.equals(authentication.getIsAuthorized())) {
                    return Mono.error(new BizbrainzPluginException(
                            BizbrainzPluginError.PLUGIN_DATASOURCE_ARGUMENT_ERROR, "Please authorize datasource"));
                }
                return Mono.from(OAuth2AuthorizationCode.create(datasourceConfiguration));
            } else {
                return Mono.empty();
            }
        } else if (authentication instanceof BasicAuth) {
            return Mono.from(BasicAuthentication.create((BasicAuth) authentication));
        } else if (authentication instanceof ApiKeyAuth) {
            return Mono.from(ApiKeyAuthentication.create((ApiKeyAuth) authentication));
        } else if (authentication instanceof BearerTokenAuth) {
            return Mono.from(BearerTokenAuthentication.create((BearerTokenAuth) authentication));
        } else {
            return Mono.empty();
        }
    }
}