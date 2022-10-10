package com.bizBrainz.server.services;


import com.bizBrainz.external.git.GitExecutor;
import com.bizBrainz.git.service.GitExecutorImpl;
import com.bizBrainz.server.configurations.EmailConfig;
import com.bizBrainz.server.helpers.GitCloudServicesUtils;
import com.bizBrainz.server.helpers.GitFileUtils;
import com.bizBrainz.server.helpers.ResponseUtils;
import com.bizBrainz.server.repositories.GitDeployKeysRepository;
import com.bizBrainz.server.services.ce.GitServiceCEImpl;
import com.bizBrainz.server.solutions.ImportExportApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Import({GitExecutorImpl.class})
public class GitServiceImpl extends GitServiceCEImpl implements GitService {
    public GitServiceImpl(UserService userService,
                          UserDataService userDataService,
                          SessionUserService sessionUserService,
                          ApplicationService applicationService,
                          ApplicationPageService applicationPageService,
                          NewPageService newPageService,
                          NewActionService newActionService,
                          ActionCollectionService actionCollectionService,
                          GitFileUtils fileUtils,
                          ImportExportApplicationService importExportApplicationService,
                          GitExecutor gitExecutor,
                          ResponseUtils responseUtils,
                          EmailConfig emailConfig,
                          AnalyticsService analyticsService,
                          GitCloudServicesUtils gitCloudServicesUtils,
                          GitDeployKeysRepository gitDeployKeysRepository,
                          DatasourceService datasourceService,
                          PluginService pluginService) {

        super(userService, userDataService, sessionUserService, applicationService, applicationPageService,
                newPageService, newActionService, actionCollectionService, fileUtils, importExportApplicationService,
                gitExecutor, responseUtils, emailConfig, analyticsService, gitCloudServicesUtils, gitDeployKeysRepository,
                datasourceService, pluginService);
    }
}
