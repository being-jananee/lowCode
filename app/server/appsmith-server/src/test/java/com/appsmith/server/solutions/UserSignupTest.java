package com.bizBrainz.server.solutions;

import com.bizBrainz.server.authentication.handlers.AuthenticationSuccessHandler;
import com.bizBrainz.server.configurations.CommonConfig;
import com.bizBrainz.server.domains.User;
import com.bizBrainz.server.exceptions.BizbrainzError;
import com.bizBrainz.server.exceptions.BizbrainzException;
import com.bizBrainz.server.helpers.PolicyUtils;
import com.bizBrainz.server.helpers.UserUtils;
import com.bizBrainz.server.services.AnalyticsService;
import com.bizBrainz.server.services.ApplicationPageService;
import com.bizBrainz.server.services.CaptchaService;
import com.bizBrainz.server.services.ConfigService;
import com.bizBrainz.server.services.UserDataService;
import com.bizBrainz.server.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.bizBrainz.server.helpers.ValidationUtils.LOGIN_PASSWORD_MAX_LENGTH;
import static com.bizBrainz.server.helpers.ValidationUtils.LOGIN_PASSWORD_MIN_LENGTH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class UserSignupTest {
    @MockBean
    private UserService userService;

    @MockBean
    private UserDataService userDataService;

    @MockBean
    private CaptchaService captchaService;

    @MockBean
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @MockBean
    private ConfigService configService;

    @MockBean
    private PolicyUtils policyUtils;

    @MockBean
    private AnalyticsService analyticsService;

    @MockBean
    private ApplicationPageService applicationPageService;

    @MockBean
    private EnvManager envManager;

    @MockBean
    private CommonConfig commonConfig;

    @MockBean
    private UserUtils userUtils;

    private UserSignup userSignup;

    @BeforeEach
    public void setup() {
        userSignup = new UserSignupImpl(userService, userDataService, captchaService, authenticationSuccessHandler,
                configService, analyticsService, envManager, commonConfig, userUtils);
    }

    private String createRandomString(int length) {
        StringBuilder builder = new StringBuilder();
        builder.append("Z".repeat(Math.max(0, length)));
        return builder.toString();
    }

    @Test
    public void signupAndLogin_WhenPasswordTooShort_RaisesException() {
        User user = new User();
        user.setEmail("testemail@test123.com");
        user.setPassword(createRandomString(LOGIN_PASSWORD_MIN_LENGTH - 1));

        Mono<User> userMono = userSignup.signupAndLogin(user, null);
        StepVerifier.create(userMono)
                .expectErrorSatisfies(error -> {
                    assertTrue(error instanceof BizbrainzException);

                    String expectedErrorMessage = BizbrainzError.INVALID_PASSWORD_LENGTH
                            .getMessage(LOGIN_PASSWORD_MIN_LENGTH, LOGIN_PASSWORD_MAX_LENGTH);
                    assertEquals(expectedErrorMessage, error.getMessage());
                })
                .verify();

    }

    @Test
    public void signupAndLogin_WhenPasswordTooLong_RaisesException() {
        User user = new User();
        user.setEmail("testemail@test123.com");
        user.setPassword(createRandomString(LOGIN_PASSWORD_MAX_LENGTH + 1));

        Mono<User> userMono = userSignup.signupAndLogin(user, null);
        StepVerifier.create(userMono)
                .expectErrorSatisfies(error -> {
                    assertTrue(error instanceof BizbrainzException);

                    String expectedErrorMessage = BizbrainzError.INVALID_PASSWORD_LENGTH
                            .getMessage(LOGIN_PASSWORD_MIN_LENGTH, LOGIN_PASSWORD_MAX_LENGTH);
                    assertEquals(expectedErrorMessage, error.getMessage());
                })
                .verify();
    }
}
