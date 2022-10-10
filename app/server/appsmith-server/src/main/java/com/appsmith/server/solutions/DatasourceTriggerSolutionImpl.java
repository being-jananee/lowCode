package com.bizBrainz.server.solutions;

import com.bizBrainz.server.helpers.PluginExecutorHelper;
import com.bizBrainz.server.services.AuthenticationValidator;
import com.bizBrainz.server.services.DatasourceContextService;
import com.bizBrainz.server.services.DatasourceService;
import com.bizBrainz.server.services.PluginService;
import com.bizBrainz.server.solutions.ce.DatasourceTriggerSolutionCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DatasourceTriggerSolutionImpl extends DatasourceTriggerSolutionCEImpl implements DatasourceTriggerSolution {

    public DatasourceTriggerSolutionImpl(DatasourceService datasourceService,
                                         PluginExecutorHelper pluginExecutorHelper,
                                         PluginService pluginService,
                                         DatasourceStructureSolution datasourceStructureSolution,
                                         AuthenticationValidator authenticationValidator,
                                         DatasourceContextService datasourceContextService) {

        super(datasourceService,
                pluginExecutorHelper,
                pluginService,
                datasourceStructureSolution,
                authenticationValidator,
                datasourceContextService);
    }
}
