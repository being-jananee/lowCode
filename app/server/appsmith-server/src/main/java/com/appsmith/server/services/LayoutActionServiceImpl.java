package com.bizBrainz.server.services;

import com.bizBrainz.server.helpers.ResponseUtils;
import com.bizBrainz.server.services.ce.LayoutActionServiceCEImpl;
import com.bizBrainz.server.solutions.PageLoadActionsUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LayoutActionServiceImpl extends LayoutActionServiceCEImpl implements LayoutActionService {

    public LayoutActionServiceImpl(ObjectMapper objectMapper,
                                   AnalyticsService analyticsService,
                                   NewPageService newPageService,
                                   NewActionService newActionService,
                                   PageLoadActionsUtil pageLoadActionsUtil,
                                   SessionUserService sessionUserService,
                                   ActionCollectionService actionCollectionService,
                                   CollectionService collectionService,
                                   ApplicationService applicationService,
                                   ResponseUtils responseUtils,
                                   DatasourceService datasourceService) {

        super(objectMapper, analyticsService, newPageService, newActionService, pageLoadActionsUtil, sessionUserService,
                actionCollectionService, collectionService, applicationService, responseUtils, datasourceService);

    }
}
