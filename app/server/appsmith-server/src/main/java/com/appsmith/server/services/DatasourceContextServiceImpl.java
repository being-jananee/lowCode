package com.bizBrainz.server.services;

import com.bizBrainz.server.helpers.PluginExecutorHelper;
import com.bizBrainz.server.services.ce.DatasourceContextServiceCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DatasourceContextServiceImpl extends DatasourceContextServiceCEImpl implements DatasourceContextService {

    public DatasourceContextServiceImpl(DatasourceService datasourceService,
                                        PluginService pluginService,
                                        PluginExecutorHelper pluginExecutorHelper,
                                        ConfigService configService) {

        super(datasourceService, pluginService, pluginExecutorHelper, configService);
    }
}
