package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.ActionCollectionControllerCE;
import com.bizBrainz.server.services.ActionCollectionService;
import com.bizBrainz.server.services.LayoutCollectionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Url.ACTION_COLLECTION_URL)
public class ActionCollectionController extends ActionCollectionControllerCE {

    public ActionCollectionController(ActionCollectionService actionCollectionService,
                                      LayoutCollectionService layoutCollectionService) {

        super(actionCollectionService, layoutCollectionService);
    }

}
