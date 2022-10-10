package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.MarketplaceControllerCE;
import com.bizBrainz.server.services.MarketplaceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Url.MARKETPLACE_URL)
@Slf4j
public class MarketplaceController extends MarketplaceControllerCE {

    public MarketplaceController(ObjectMapper objectMapper,
                                 MarketplaceService marketplaceService) {

        super(objectMapper, marketplaceService);
    }
}
