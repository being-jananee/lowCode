package com.bizBrainz.server.services;

import com.bizBrainz.server.helpers.ResponseUtils;
import com.bizBrainz.server.services.ce.LayoutServiceCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LayoutServiceImpl extends LayoutServiceCEImpl implements LayoutService {

    public LayoutServiceImpl(NewPageService newPageService, ResponseUtils responseUtils) {
        super(newPageService, responseUtils);
    }
}

