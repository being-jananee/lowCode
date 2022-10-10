package com.bizBrainz.server.services;

import com.bizBrainz.server.repositories.ApplicationRepository;
import com.bizBrainz.server.repositories.UserDataRepository;
import com.bizBrainz.server.repositories.UserRepository;
import com.bizBrainz.server.services.ce.UserDataServiceCEImpl;
import com.bizBrainz.server.solutions.ReleaseNotesService;
import com.bizBrainz.server.solutions.UserChangedHandler;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Scheduler;

import javax.validation.Validator;

@Service
public class UserDataServiceImpl extends UserDataServiceCEImpl implements UserDataService {

    public UserDataServiceImpl(Scheduler scheduler,
                               Validator validator,
                               MongoConverter mongoConverter,
                               ReactiveMongoTemplate reactiveMongoTemplate,
                               UserDataRepository repository,
                               AnalyticsService analyticsService,
                               UserRepository userRepository,
                               SessionUserService sessionUserService,
                               AssetService assetService,
                               ReleaseNotesService releaseNotesService,
                               FeatureFlagService featureFlagService,
                               UserChangedHandler userChangedHandler,
                               ApplicationRepository applicationRepository,
                               TenantService tenantService) {

        super(scheduler, validator, mongoConverter, reactiveMongoTemplate, repository, analyticsService, userRepository,
                sessionUserService, assetService, releaseNotesService, featureFlagService, userChangedHandler,
                applicationRepository, tenantService);
    }
}
