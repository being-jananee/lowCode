package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.RestApiImportControllerCE;
import com.bizBrainz.server.services.CurlImporterService;
import com.bizBrainz.server.services.PostmanImporterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Url.IMPORT_URL)
@Slf4j
public class RestApiImportController extends RestApiImportControllerCE {

    public RestApiImportController(CurlImporterService curlImporterService,
                                   PostmanImporterService postmanImporterService) {

        super(curlImporterService, postmanImporterService);
    }
}
