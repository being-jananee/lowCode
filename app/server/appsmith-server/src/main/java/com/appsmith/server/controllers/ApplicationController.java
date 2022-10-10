package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.ApplicationControllerCE;
import com.bizBrainz.server.services.ApplicationPageService;
import com.bizBrainz.server.services.ApplicationService;
import com.bizBrainz.server.services.ThemeService;
import com.bizBrainz.server.solutions.ApplicationFetcher;
import com.bizBrainz.server.solutions.ApplicationForkingService;
import com.bizBrainz.server.solutions.ImportExportApplicationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Url.APPLICATION_URL)
public class ApplicationController extends ApplicationControllerCE {

    public ApplicationController(ApplicationService service,
                                 ApplicationPageService applicationPageService,
                                 ApplicationFetcher applicationFetcher,
                                 ApplicationForkingService applicationForkingService,
                                 ImportExportApplicationService importExportApplicationService,
                                 ThemeService themeService) {

        super(service, applicationPageService, applicationFetcher, applicationForkingService,
                importExportApplicationService, themeService);

    }
}
