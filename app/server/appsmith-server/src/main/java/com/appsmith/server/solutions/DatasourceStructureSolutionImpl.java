package com.bizBrainz.server.solutions;

import com.bizBrainz.server.helpers.PluginExecutorHelper;
import com.bizBrainz.server.repositories.CustomDatasourceRepository;
import com.bizBrainz.server.services.AuthenticationValidator;
import com.bizBrainz.server.services.DatasourceContextService;
import com.bizBrainz.server.services.DatasourceService;
import com.bizBrainz.server.services.PluginService;
import com.bizBrainz.server.solutions.ce.DatasourceStructureSolutionCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DatasourceStructureSolutionImpl extends DatasourceStructureSolutionCEImpl implements DatasourceStructureSolution {

    public DatasourceStructureSolutionImpl(DatasourceService datasourceService,
                                           PluginExecutorHelper pluginExecutorHelper,
                                           PluginService pluginService,
                                           DatasourceContextService datasourceContextService,
                                           CustomDatasourceRepository datasourceRepository,
                                           AuthenticationValidator authenticationValidator) {

        super(datasourceService, pluginExecutorHelper, pluginService, datasourceContextService, datasourceRepository,
                authenticationValidator);
    }
}
