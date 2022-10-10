package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.ApplicationTemplateControllerCE;
import com.bizBrainz.server.services.ApplicationTemplateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Url.APP_TEMPLATE_URL)
public class ApplicationTemplateController extends ApplicationTemplateControllerCE {

    public ApplicationTemplateController(ApplicationTemplateService applicationTemplateService) {
        super(applicationTemplateService);
    }
}
