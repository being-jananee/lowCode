package com.bizBrainz.server.services;

import com.bizBrainz.server.acl.PolicyGenerator;
import com.bizBrainz.server.helpers.PluginExecutorHelper;
import com.bizBrainz.server.helpers.PolicyUtils;
import com.bizBrainz.server.helpers.ResponseUtils;
import com.bizBrainz.server.repositories.NewActionRepository;
import com.bizBrainz.server.services.ce.NewActionServiceCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Scheduler;

import javax.validation.Validator;

@Service
@Slf4j
public class NewActionServiceImpl extends NewActionServiceCEImpl implements NewActionService {

    public NewActionServiceImpl(Scheduler scheduler,
                                Validator validator,
                                MongoConverter mongoConverter,
                                ReactiveMongoTemplate reactiveMongoTemplate,
                                NewActionRepository repository,
                                AnalyticsService analyticsService,
                                DatasourceService datasourceService,
                                PluginService pluginService,
                                DatasourceContextService datasourceContextService,
                                PluginExecutorHelper pluginExecutorHelper,
                                MarketplaceService marketplaceService,
                                PolicyGenerator policyGenerator,
                                NewPageService newPageService,
                                ApplicationService applicationService,
                                SessionUserService sessionUserService,
                                PolicyUtils policyUtils,
                                AuthenticationValidator authenticationValidator,
                                ConfigService configService,
                                ResponseUtils responseUtils,
                                PermissionGroupService permissionGroupService) {

        super(scheduler, validator, mongoConverter, reactiveMongoTemplate, repository, analyticsService,
                datasourceService, pluginService, datasourceContextService, pluginExecutorHelper, marketplaceService,
                policyGenerator, newPageService, applicationService, sessionUserService, policyUtils,
                authenticationValidator, configService, responseUtils, permissionGroupService);

    }
}
