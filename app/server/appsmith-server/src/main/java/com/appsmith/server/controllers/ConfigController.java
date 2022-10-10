package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.ConfigControllerCE;
import com.bizBrainz.server.services.ConfigService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Url.CONFIG_URL)
public class ConfigController extends ConfigControllerCE {

    public ConfigController(ConfigService service) {
        super(service);
    }
}
