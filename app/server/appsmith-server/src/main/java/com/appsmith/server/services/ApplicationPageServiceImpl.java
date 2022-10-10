package com.bizBrainz.server.services;

import com.bizBrainz.server.acl.PolicyGenerator;
import com.bizBrainz.server.helpers.GitFileUtils;
import com.bizBrainz.server.helpers.ResponseUtils;
import com.bizBrainz.server.repositories.ApplicationRepository;
import com.bizBrainz.server.repositories.CommentThreadRepository;
import com.bizBrainz.server.repositories.WorkspaceRepository;
import com.bizBrainz.server.services.ce.ApplicationPageServiceCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApplicationPageServiceImpl extends ApplicationPageServiceCEImpl implements ApplicationPageService {

    public ApplicationPageServiceImpl(WorkspaceService workspaceService,
                                      ApplicationService applicationService,
                                      SessionUserService sessionUserService,
                                      WorkspaceRepository workspaceRepository,
                                      LayoutActionService layoutActionService,
                                      AnalyticsService analyticsService,
                                      PolicyGenerator policyGenerator,
                                      ApplicationRepository applicationRepository,
                                      NewPageService newPageService,
                                      NewActionService newActionService,
                                      ActionCollectionService actionCollectionService,
                                      GitFileUtils gitFileUtils,
                                      CommentThreadRepository commentThreadRepository,
                                      ThemeService themeService,
                                      ResponseUtils responseUtils) {

        super(workspaceService, applicationService, sessionUserService, workspaceRepository, layoutActionService, analyticsService,
                policyGenerator, applicationRepository, newPageService, newActionService, actionCollectionService,
                gitFileUtils, commentThreadRepository, themeService, responseUtils);
    }
}
