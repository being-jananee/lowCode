package com.bizBrainz.server.services;

import com.bizBrainz.external.services.EncryptionService;
import com.bizBrainz.server.configurations.CommonConfig;
import com.bizBrainz.server.configurations.EmailConfig;
import com.bizBrainz.server.helpers.PolicyUtils;
import com.bizBrainz.server.helpers.UserUtils;
import com.bizBrainz.server.notifications.EmailSender;
import com.bizBrainz.server.repositories.ApplicationRepository;
import com.bizBrainz.server.repositories.PasswordResetTokenRepository;
import com.bizBrainz.server.repositories.UserRepository;
import com.bizBrainz.server.services.ce.UserServiceCEImpl;
import com.bizBrainz.server.solutions.UserChangedHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Scheduler;

import javax.validation.Validator;

@Slf4j
@Service
public class UserServiceImpl extends UserServiceCEImpl implements UserService {


    public UserServiceImpl(Scheduler scheduler,
                           Validator validator,
                           MongoConverter mongoConverter,
                           ReactiveMongoTemplate reactiveMongoTemplate,
                           UserRepository repository,
                           WorkspaceService workspaceService,
                           AnalyticsService analyticsService,
                           SessionUserService sessionUserService,
                           PasswordResetTokenRepository passwordResetTokenRepository,
                           PasswordEncoder passwordEncoder,
                           EmailSender emailSender,
                           ApplicationRepository applicationRepository,
                           PolicyUtils policyUtils,
                           CommonConfig commonConfig,
                           EmailConfig emailConfig,
                           UserChangedHandler userChangedHandler,
                           EncryptionService encryptionService,
                           UserDataService userDataService,
                           TenantService tenantService,
                           PermissionGroupService permissionGroupService,
                           UserUtils userUtils) {

        super(scheduler, validator, mongoConverter, reactiveMongoTemplate, repository, workspaceService, analyticsService,
                sessionUserService, passwordResetTokenRepository, passwordEncoder, emailSender, applicationRepository,
                policyUtils, commonConfig, emailConfig, userChangedHandler, encryptionService, userDataService, tenantService,
                permissionGroupService, userUtils);
    }
}
