package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.LayoutControllerCE;
import com.bizBrainz.server.services.LayoutActionService;
import com.bizBrainz.server.services.LayoutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Url.LAYOUT_URL)
@Slf4j
public class LayoutController extends LayoutControllerCE {

    public LayoutController(LayoutService layoutService,
                            LayoutActionService layoutActionService) {

        super(layoutService, layoutActionService);
    }

}
