package com.bizBrainz.server.services;

import com.bizBrainz.server.helpers.ResponseUtils;
import com.bizBrainz.server.services.ce.CurlImporterServiceCEImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CurlImporterServiceImpl extends CurlImporterServiceCEImpl implements CurlImporterService {

    public CurlImporterServiceImpl(
            PluginService pluginService,
            LayoutActionService layoutActionService,
            NewPageService newPageService,
            ResponseUtils responseUtils,
            ObjectMapper objectMapper
    ) {
        super(pluginService, layoutActionService, newPageService, responseUtils, objectMapper);
    }
}
