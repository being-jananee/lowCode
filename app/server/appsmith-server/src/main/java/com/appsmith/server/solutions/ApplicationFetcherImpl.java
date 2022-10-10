package com.bizBrainz.server.solutions;

import com.bizBrainz.server.helpers.ResponseUtils;
import com.bizBrainz.server.repositories.ApplicationRepository;
import com.bizBrainz.server.services.NewPageService;
import com.bizBrainz.server.services.WorkspaceService;
import com.bizBrainz.server.services.SessionUserService;
import com.bizBrainz.server.services.UserDataService;
import com.bizBrainz.server.services.UserService;
import com.bizBrainz.server.services.UserWorkspaceService;
import com.bizBrainz.server.solutions.ce.ApplicationFetcherCEImpl;
import org.springframework.stereotype.Component;


@Component
public class ApplicationFetcherImpl extends ApplicationFetcherCEImpl implements ApplicationFetcher {

    public ApplicationFetcherImpl(SessionUserService sessionUserService,
                                  UserService userService,
                                  UserDataService userDataService,
                                  WorkspaceService workspaceService,
                                  ApplicationRepository applicationRepository,
                                  ReleaseNotesService releaseNotesService,
                                  ResponseUtils responseUtils,
                                  NewPageService newPageService,
                                  UserWorkspaceService userWorkspaceService) {

        super(sessionUserService, userService, userDataService, workspaceService, applicationRepository,
                releaseNotesService, responseUtils, newPageService, userWorkspaceService);
    }
}
