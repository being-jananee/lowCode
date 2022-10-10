package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.ApiTemplateControllerCE;
import com.bizBrainz.server.services.ApiTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Url.API_TEMPLATE_URL)
@Slf4j
public class ApiTemplateController extends ApiTemplateControllerCE {

    public ApiTemplateController(ApiTemplateService service) {
        super(service);
    }

}
