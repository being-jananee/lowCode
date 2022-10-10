package com.bizBrainz.server.solutions;

import com.bizBrainz.server.configurations.CloudServicesConfig;
import com.bizBrainz.server.services.ConfigService;
import com.bizBrainz.server.services.PluginService;
import com.bizBrainz.server.solutions.ce.PluginScheduledTaskCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class PluginScheduledTaskImpl extends PluginScheduledTaskCEImpl implements PluginScheduledTask {

    public PluginScheduledTaskImpl(ConfigService configService,
                                   PluginService pluginService,
                                   CloudServicesConfig cloudServicesConfig) {

        super(configService, pluginService, cloudServicesConfig);
    }
}
