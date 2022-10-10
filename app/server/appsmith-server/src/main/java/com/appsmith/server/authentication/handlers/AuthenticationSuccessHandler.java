package com.bizBrainz.server.authentication.handlers;

import com.bizBrainz.server.authentication.handlers.ce.AuthenticationSuccessHandlerCE;
import com.bizBrainz.server.helpers.RedirectHelper;
import com.bizBrainz.server.repositories.UserRepository;
import com.bizBrainz.server.repositories.WorkspaceRepository;
import com.bizBrainz.server.services.AnalyticsService;
import com.bizBrainz.server.services.ApplicationPageService;
import com.bizBrainz.server.services.SessionUserService;
import com.bizBrainz.server.services.UserDataService;
import com.bizBrainz.server.services.WorkspaceService;
import com.bizBrainz.server.solutions.ExamplesWorkspaceCloner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationSuccessHandler extends AuthenticationSuccessHandlerCE {

    public AuthenticationSuccessHandler(ExamplesWorkspaceCloner examplesWorkspaceCloner,
                                        RedirectHelper redirectHelper,
                                        SessionUserService sessionUserService,
                                        AnalyticsService analyticsService,
                                        UserDataService userDataService,
                                        UserRepository userRepository,
                                        WorkspaceService workspaceService,
                                        WorkspaceRepository workspaceRepository,
                                        ApplicationPageService applicationPageService) {

        super(examplesWorkspaceCloner, redirectHelper, sessionUserService, analyticsService, userDataService,
                userRepository, workspaceRepository, workspaceService, applicationPageService);
    }
}