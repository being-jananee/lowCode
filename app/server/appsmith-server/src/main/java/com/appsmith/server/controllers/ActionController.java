package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.ActionControllerCE;
import com.bizBrainz.server.services.ActionCollectionService;
import com.bizBrainz.server.services.LayoutActionService;
import com.bizBrainz.server.services.NewActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Url.ACTION_URL)
@Slf4j
public class ActionController extends ActionControllerCE {

    public ActionController(ActionCollectionService actionCollectionService,
                            LayoutActionService layoutActionService,
                            NewActionService newActionService) {

        super(layoutActionService, newActionService);

    }

}
