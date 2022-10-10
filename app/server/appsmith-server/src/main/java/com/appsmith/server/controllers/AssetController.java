package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.AssetControllerCE;
import com.bizBrainz.server.services.AssetService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Url.ASSET_URL)
public class AssetController extends AssetControllerCE {

    public AssetController(AssetService service) {
        super(service);
    }
}
