package com.bizBrainz.server.solutions;

import com.bizBrainz.server.helpers.PolicyUtils;
import com.bizBrainz.server.repositories.ActionCollectionRepository;
import com.bizBrainz.server.repositories.DatasourceRepository;
import com.bizBrainz.server.repositories.NewActionRepository;
import com.bizBrainz.server.repositories.NewPageRepository;
import com.bizBrainz.server.repositories.PluginRepository;
import com.bizBrainz.server.services.ActionCollectionService;
import com.bizBrainz.server.services.AnalyticsService;
import com.bizBrainz.server.services.ApplicationPageService;
import com.bizBrainz.server.services.ApplicationService;
import com.bizBrainz.server.services.DatasourceService;
import com.bizBrainz.server.services.NewActionService;
import com.bizBrainz.server.services.NewPageService;
import com.bizBrainz.server.services.SequenceService;
import com.bizBrainz.server.services.SessionUserService;
import com.bizBrainz.server.services.ThemeService;
import com.bizBrainz.server.services.WorkspaceService;
import com.bizBrainz.server.solutions.ce.ImportExportApplicationServiceCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ImportExportApplicationServiceImpl extends ImportExportApplicationServiceCEImpl implements ImportExportApplicationService {

    public ImportExportApplicationServiceImpl(DatasourceService datasourceService,
                                              SessionUserService sessionUserService,
                                              NewActionRepository newActionRepository,
                                              DatasourceRepository datasourceRepository,
                                              PluginRepository pluginRepository,
                                              WorkspaceService workspaceService,
                                              ApplicationService applicationService,
                                              NewPageService newPageService,
                                              ApplicationPageService applicationPageService,
                                              NewPageRepository newPageRepository,
                                              NewActionService newActionService,
                                              SequenceService sequenceService,
                                              ExamplesWorkspaceCloner examplesWorkspaceCloner,
                                              ActionCollectionRepository actionCollectionRepository,
                                              ActionCollectionService actionCollectionService,
                                              ThemeService themeService,
                                              PolicyUtils policyUtils,
                                              AnalyticsService analyticsService) {

        super(datasourceService, sessionUserService, newActionRepository, datasourceRepository, pluginRepository,
                workspaceService, applicationService, newPageService, applicationPageService, newPageRepository,
                newActionService, sequenceService, examplesWorkspaceCloner, actionCollectionRepository,
                actionCollectionService, themeService, policyUtils, analyticsService);
    }
}
