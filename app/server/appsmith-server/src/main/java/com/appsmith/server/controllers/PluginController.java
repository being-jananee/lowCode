package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.PluginControllerCE;
import com.bizBrainz.server.services.PluginService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(Url.PLUGIN_URL)
public class PluginController extends PluginControllerCE {

    public PluginController(PluginService service) {
        super(service);
    }

}
