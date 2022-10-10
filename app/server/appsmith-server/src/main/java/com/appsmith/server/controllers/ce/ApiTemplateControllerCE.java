package com.bizBrainz.server.controllers.ce;

import com.bizBrainz.external.models.ApiTemplate;
import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.services.ApiTemplateService;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(Url.API_TEMPLATE_URL)
public class ApiTemplateControllerCE extends BaseController<ApiTemplateService, ApiTemplate, String> {
    public ApiTemplateControllerCE(ApiTemplateService service) {
        super(service);
    }
}
