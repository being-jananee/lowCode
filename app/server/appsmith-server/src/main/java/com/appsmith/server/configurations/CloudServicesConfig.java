package com.bizBrainz.server.configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class CloudServicesConfig {
    @Value("${bizBrainz.cloud_services.base_url}")
    String baseUrl;

    @Value("${bizBrainz.cloud_services.username}")
    private String username;

    @Value("${bizBrainz.cloud_services.password}")
    private String password;
}
