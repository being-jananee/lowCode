package com.bizBrainz.server.solutions;

import com.bizBrainz.server.helpers.PluginExecutorHelper;
import com.bizBrainz.server.helpers.ResponseUtils;
import com.bizBrainz.server.services.AnalyticsService;
import com.bizBrainz.server.services.ApplicationPageService;
import com.bizBrainz.server.services.ApplicationService;
import com.bizBrainz.server.services.DatasourceService;
import com.bizBrainz.server.services.LayoutActionService;
import com.bizBrainz.server.services.NewPageService;
import com.bizBrainz.server.services.PluginService;
import com.bizBrainz.server.services.SessionUserService;
import com.bizBrainz.server.solutions.ce.CreateDBTablePageSolutionCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateDBTablePageSolutionImpl extends CreateDBTablePageSolutionCEImpl implements CreateDBTablePageSolution {

    public CreateDBTablePageSolutionImpl(DatasourceService datasourceService,
                                         NewPageService newPageService,
                                         LayoutActionService layoutActionService,
                                         ApplicationPageService applicationPageService,
                                         ApplicationService applicationService,
                                         PluginService pluginService,
                                         AnalyticsService analyticsService,
                                         SessionUserService sessionUserService,
                                         ResponseUtils responseUtils,
                                         PluginExecutorHelper pluginExecutorHelper) {

        super(datasourceService, newPageService, layoutActionService, applicationPageService, applicationService,
                pluginService, analyticsService, sessionUserService, responseUtils, pluginExecutorHelper);
    }
}