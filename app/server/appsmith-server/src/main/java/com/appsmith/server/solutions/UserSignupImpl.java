package com.bizBrainz.server.solutions;

import com.bizBrainz.server.authentication.handlers.AuthenticationSuccessHandler;
import com.bizBrainz.server.configurations.CommonConfig;
import com.bizBrainz.server.helpers.UserUtils;
import com.bizBrainz.server.services.AnalyticsService;
import com.bizBrainz.server.services.CaptchaService;
import com.bizBrainz.server.services.ConfigService;
import com.bizBrainz.server.services.UserDataService;
import com.bizBrainz.server.services.UserService;
import com.bizBrainz.server.solutions.ce.UserSignupCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserSignupImpl extends UserSignupCEImpl implements UserSignup {

    public UserSignupImpl(UserService userService,
                          UserDataService userDataService,
                          CaptchaService captchaService,
                          AuthenticationSuccessHandler authenticationSuccessHandler,
                          ConfigService configService,
                          AnalyticsService analyticsService,
                          EnvManager envManager,
                          CommonConfig commonConfig,
                          UserUtils userUtils) {

        super(userService, userDataService, captchaService, authenticationSuccessHandler, configService,
                analyticsService, envManager, commonConfig, userUtils);
    }
}
