package com.bizBrainz.server.services;

import com.bizBrainz.server.helpers.ResponseUtils;
import com.bizBrainz.server.services.ce.PostmanImporterServiceCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PostmanImporterServiceImpl extends PostmanImporterServiceCEImpl implements PostmanImporterService {

    public PostmanImporterServiceImpl(NewPageService newPageService,
                                      ResponseUtils responseUtils) {
        super(newPageService, responseUtils);
    }
}
