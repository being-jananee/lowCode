package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.WorkspaceControllerCE;
import com.bizBrainz.server.services.WorkspaceService;
import com.bizBrainz.server.services.UserWorkspaceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Url.WORKSPACE_URL)
public class WorkspaceController extends WorkspaceControllerCE {

    public WorkspaceController(WorkspaceService workspaceService,
                                  UserWorkspaceService userWorkspaceService) {

        super(workspaceService, userWorkspaceService);
    }
}
