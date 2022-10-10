package com.bizBrainz.server.services;

import com.bizBrainz.server.configurations.CloudServicesConfig;
import com.bizBrainz.server.helpers.ResponseUtils;
import com.bizBrainz.server.services.ce.ApplicationTemplateServiceCEImpl;
import com.bizBrainz.server.solutions.ImportExportApplicationService;
import com.bizBrainz.server.solutions.ReleaseNotesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApplicationTemplateServiceImpl extends ApplicationTemplateServiceCEImpl implements ApplicationTemplateService {
    public ApplicationTemplateServiceImpl(CloudServicesConfig cloudServicesConfig,
                                          ReleaseNotesService releaseNotesService,
                                          ImportExportApplicationService importExportApplicationService,
                                          AnalyticsService analyticsService,
                                          UserDataService userDataService,
                                          ApplicationService applicationService,
                                          ResponseUtils responseUtils) {
        super(cloudServicesConfig, releaseNotesService, importExportApplicationService, analyticsService, userDataService, applicationService, responseUtils);
    }
}
