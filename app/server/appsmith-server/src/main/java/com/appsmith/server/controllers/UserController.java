package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.UserControllerCE;
import com.bizBrainz.server.services.SessionUserService;
import com.bizBrainz.server.services.UserDataService;
import com.bizBrainz.server.services.UserWorkspaceService;
import com.bizBrainz.server.services.UserService;
import com.bizBrainz.server.solutions.UserSignup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Url.USER_URL)
@Slf4j
public class UserController extends UserControllerCE {

    public UserController(UserService service,
                          SessionUserService sessionUserService,
                          UserWorkspaceService userWorkspaceService,
                          UserSignup userSignup,
                          UserDataService userDataService) {

        super(service, sessionUserService, userWorkspaceService, userSignup, userDataService);
    }
}
