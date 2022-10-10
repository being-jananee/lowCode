package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.UsagePulseControllerCE;
import com.bizBrainz.server.services.UsagePulseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Url.USAGE_PULSE_URL)
public class UsagePulseController extends UsagePulseControllerCE {

    public UsagePulseController(UsagePulseService service) {
        super(service);
    }

}
