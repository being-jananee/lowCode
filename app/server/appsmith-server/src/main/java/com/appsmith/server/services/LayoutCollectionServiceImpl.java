package com.bizBrainz.server.services;

import com.bizBrainz.server.helpers.ResponseUtils;
import com.bizBrainz.server.services.ce.LayoutCollectionServiceCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LayoutCollectionServiceImpl extends LayoutCollectionServiceCEImpl implements LayoutCollectionService {

    public LayoutCollectionServiceImpl(NewPageService newPageService,
                                       LayoutActionService layoutActionService,
                                       ActionCollectionService actionCollectionService,
                                       NewActionService newActionService,
                                       AnalyticsService analyticsService,
                                       ResponseUtils responseUtils) {

        super(newPageService, layoutActionService, actionCollectionService, newActionService, analyticsService,
                responseUtils);
    }
}
