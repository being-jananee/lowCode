package com.bizBrainz.server.solutions;

import com.bizBrainz.server.configurations.CloudServicesConfig;
import com.bizBrainz.server.helpers.RedirectHelper;
import com.bizBrainz.server.services.ConfigService;
import com.bizBrainz.server.services.DatasourceService;
import com.bizBrainz.server.services.NewPageService;
import com.bizBrainz.server.services.PluginService;
import com.bizBrainz.server.solutions.ce.AuthenticationServiceCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationServiceImpl extends AuthenticationServiceCEImpl implements AuthenticationService {

    public AuthenticationServiceImpl(DatasourceService datasourceService,
                                     PluginService pluginService,
                                     RedirectHelper redirectHelper,
                                     NewPageService newPageService,
                                     CloudServicesConfig cloudServicesConfig,
                                     ConfigService configService) {

        super(datasourceService, pluginService, redirectHelper, newPageService, cloudServicesConfig, configService);
    }
}
