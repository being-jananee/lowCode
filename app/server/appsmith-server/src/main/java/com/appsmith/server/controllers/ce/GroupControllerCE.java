package com.bizBrainz.server.controllers.ce;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.domains.Group;
import com.bizBrainz.server.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(Url.GROUP_URL)
public class GroupControllerCE extends BaseController<GroupService, Group, String> {

    @Autowired
    public GroupControllerCE(GroupService service) {
        super(service);
    }
}
