package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.GroupControllerCE;
import com.bizBrainz.server.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Url.GROUP_URL)
public class GroupController extends GroupControllerCE {

    public GroupController(GroupService service) {
        super(service);
    }
}
