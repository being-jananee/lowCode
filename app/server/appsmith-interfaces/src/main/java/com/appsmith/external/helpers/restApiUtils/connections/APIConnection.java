package com.bizBrainz.external.helpers.restApiUtils.connections;

import com.bizBrainz.external.helpers.SSLHelper;
import com.bizBrainz.external.models.DatasourceConfiguration;
import com.bizBrainz.external.models.OAuth2;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.netty.http.client.HttpClient;

// Parent type for all API connections that need to be created during datasource create method.
public abstract class APIConnection implements ExchangeFilterFunction {

    HttpClient getSecuredHttpClient(DatasourceConfiguration datasourceConfiguration) {
        final OAuth2 oAuth2 = (OAuth2) datasourceConfiguration.getAuthentication();
        HttpClient httpClient = HttpClient.create();

        if (oAuth2.isUseSelfSignedCert()) {
            httpClient = httpClient.secure(SSLHelper.sslCheckForHttpClient(datasourceConfiguration));
        }

        return httpClient;
    }
}