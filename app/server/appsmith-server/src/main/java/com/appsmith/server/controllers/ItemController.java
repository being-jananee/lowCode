package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.ItemControllerCE;
import com.bizBrainz.server.services.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(Url.MARKETPLACE_ITEM_URL)
public class ItemController extends ItemControllerCE {

    public ItemController(ItemService service) {
        super(service);
    }
}
