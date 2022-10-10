package com.bizBrainz.server.services;

import com.bizBrainz.server.helpers.PolicyUtils;
import com.bizBrainz.server.helpers.ResponseUtils;
import com.bizBrainz.server.repositories.ApplicationRepository;
import com.bizBrainz.server.repositories.CommentThreadRepository;
import com.bizBrainz.server.repositories.UserRepository;
import com.bizBrainz.server.services.ce.ApplicationServiceCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Scheduler;

import javax.validation.Validator;


@Slf4j
@Service
public class ApplicationServiceImpl extends ApplicationServiceCEImpl implements ApplicationService {

    public ApplicationServiceImpl(Scheduler scheduler,
                                  Validator validator,
                                  MongoConverter mongoConverter,
                                  ReactiveMongoTemplate reactiveMongoTemplate,
                                  ApplicationRepository repository,
                                  AnalyticsService analyticsService,
                                  PolicyUtils policyUtils,
                                  ConfigService configService,
                                  CommentThreadRepository commentThreadRepository,
                                  SessionUserService sessionUserService,
                                  ResponseUtils responseUtils,
                                  PermissionGroupService permissionGroupService,
                                  TenantService tenantService,
                                  UserRepository userRepository) {

        super(scheduler, validator, mongoConverter, reactiveMongoTemplate, repository, analyticsService, policyUtils,
                configService, commentThreadRepository, sessionUserService, responseUtils, permissionGroupService, tenantService, userRepository);

    }
}
