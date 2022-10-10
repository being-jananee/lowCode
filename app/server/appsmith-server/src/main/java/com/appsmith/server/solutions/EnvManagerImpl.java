package com.bizBrainz.server.solutions;

import com.bizBrainz.server.configurations.CommonConfig;
import com.bizBrainz.server.configurations.EmailConfig;
import com.bizBrainz.server.configurations.GoogleRecaptchaConfig;
import com.bizBrainz.server.helpers.FileUtils;
import com.bizBrainz.server.helpers.PolicyUtils;
import com.bizBrainz.server.helpers.UserUtils;
import com.bizBrainz.server.notifications.EmailSender;
import com.bizBrainz.server.repositories.UserRepository;
import com.bizBrainz.server.services.AnalyticsService;
import com.bizBrainz.server.services.ConfigService;
import com.bizBrainz.server.services.PermissionGroupService;
import com.bizBrainz.server.services.SessionUserService;
import com.bizBrainz.server.services.UserService;
import com.bizBrainz.server.solutions.ce.EnvManagerCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EnvManagerImpl extends EnvManagerCEImpl implements EnvManager {

    public EnvManagerImpl(SessionUserService sessionUserService,
                          UserService userService,
                          AnalyticsService analyticsService,
                          UserRepository userRepository,
                          PolicyUtils policyUtils,
                          EmailSender emailSender,
                          CommonConfig commonConfig,
                          EmailConfig emailConfig,
                          JavaMailSender javaMailSender,
                          GoogleRecaptchaConfig googleRecaptchaConfig,
                          FileUtils fileUtils,
                          PermissionGroupService permissionGroupService,
                          ConfigService configService,
                          UserUtils userUtils) {

            super(sessionUserService, userService, analyticsService, userRepository, policyUtils, emailSender, commonConfig,
                    emailConfig, javaMailSender, googleRecaptchaConfig, fileUtils, permissionGroupService, configService,
                    userUtils);
    }
}
