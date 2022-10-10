package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.PageControllerCE;
import com.bizBrainz.server.services.ApplicationPageService;
import com.bizBrainz.server.services.NewPageService;
import com.bizBrainz.server.solutions.CreateDBTablePageSolution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Url.PAGE_URL)
@Slf4j
public class PageController extends PageControllerCE {

    public PageController(ApplicationPageService applicationPageService,
                          NewPageService newPageService,
                          CreateDBTablePageSolution createDBTablePageSolution) {

        super(applicationPageService, newPageService, createDBTablePageSolution);
    }
}
