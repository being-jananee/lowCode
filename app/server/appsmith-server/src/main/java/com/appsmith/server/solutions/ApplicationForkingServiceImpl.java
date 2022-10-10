package com.bizBrainz.server.solutions;

import com.bizBrainz.server.helpers.PolicyUtils;
import com.bizBrainz.server.helpers.ResponseUtils;
import com.bizBrainz.server.services.AnalyticsService;
import com.bizBrainz.server.services.ApplicationService;
import com.bizBrainz.server.services.WorkspaceService;
import com.bizBrainz.server.services.SessionUserService;
import com.bizBrainz.server.solutions.ce.ApplicationForkingServiceCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApplicationForkingServiceImpl extends ApplicationForkingServiceCEImpl implements ApplicationForkingService {

    public ApplicationForkingServiceImpl(ApplicationService applicationService,
                                         WorkspaceService workspaceService,
                                         ExamplesWorkspaceCloner examplesWorkspaceCloner,
                                         PolicyUtils policyUtils,
                                         SessionUserService sessionUserService,
                                         AnalyticsService analyticsService,
                                         ResponseUtils responseUtils) {

        super(applicationService, workspaceService, examplesWorkspaceCloner, policyUtils, sessionUserService,
                analyticsService, responseUtils);
    }
}
