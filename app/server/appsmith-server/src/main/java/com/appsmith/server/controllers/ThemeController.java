package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.ThemeControllerCE;
import com.bizBrainz.server.services.ThemeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(Url.THEME_URL)
public class ThemeController extends ThemeControllerCE {

    public ThemeController(ThemeService themeService) {
        super(themeService);
    }
}
