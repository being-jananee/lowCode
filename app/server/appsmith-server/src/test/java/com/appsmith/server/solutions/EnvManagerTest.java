package com.bizBrainz.server.solutions;

import com.bizBrainz.server.configurations.CommonConfig;
import com.bizBrainz.server.configurations.EmailConfig;
import com.bizBrainz.server.configurations.GoogleRecaptchaConfig;
import com.bizBrainz.server.domains.User;
import com.bizBrainz.server.exceptions.BizbrainzError;
import com.bizBrainz.server.exceptions.BizbrainzException;
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
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@Slf4j
public class EnvManagerTest {
    @MockBean
    private SessionUserService sessionUserService;
    @MockBean
    private UserService userService;
    @MockBean
    private AnalyticsService analyticsService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PolicyUtils policyUtils;
    @MockBean
    private EmailSender emailSender;
    @MockBean
    private CommonConfig commonConfig;
    @MockBean
    private EmailConfig emailConfig;
    @MockBean
    private JavaMailSender javaMailSender;
    @MockBean
    private GoogleRecaptchaConfig googleRecaptchaConfig;
    @MockBean
    private FileUtils fileUtils;
    @MockBean
    private ConfigService configService;
    @MockBean
    private PermissionGroupService permissionGroupService;

    @MockBean
    private UserUtils userUtils;

    EnvManager envManager;

    @BeforeEach
    public void setup() {
        envManager = new EnvManagerImpl(sessionUserService,
                userService,
                analyticsService,
                userRepository,
                policyUtils,
                emailSender,
                commonConfig,
                emailConfig,
                javaMailSender,
                googleRecaptchaConfig,
                fileUtils,
                permissionGroupService,
                configService,
                userUtils);
    }

    @Test
    public void simpleSample() {
        final String content = "BIZBRAINZ_MONGODB_URI='first value'\nBIZBRAINZ_REDIS_URL='second value'\n\nBIZBRAINZ_INSTANCE_NAME='third value'";

        assertThat(envManager.transformEnvContent(
                content,
                Map.of("BIZBRAINZ_MONGODB_URI", "new first value")
        )).containsExactly(
                "BIZBRAINZ_MONGODB_URI='new first value'",
                "BIZBRAINZ_REDIS_URL='second value'",
                "",
                "BIZBRAINZ_INSTANCE_NAME='third value'"
        );

        assertThat(envManager.transformEnvContent(
                content,
                Map.of("BIZBRAINZ_REDIS_URL", "new second value")
        )).containsExactly(
                "BIZBRAINZ_MONGODB_URI='first value'",
                "BIZBRAINZ_REDIS_URL='new second value'",
                "",
                "BIZBRAINZ_INSTANCE_NAME='third value'"
        );

        assertThat(envManager.transformEnvContent(
                content,
                Map.of("BIZBRAINZ_INSTANCE_NAME", "new third value")
        )).containsExactly(
                "BIZBRAINZ_MONGODB_URI='first value'",
                "BIZBRAINZ_REDIS_URL='second value'",
                "",
                "BIZBRAINZ_INSTANCE_NAME='new third value'"
        );

        assertThat(envManager.transformEnvContent(
                content,
                Map.of(
                        "BIZBRAINZ_MONGODB_URI", "new first value",
                        "BIZBRAINZ_INSTANCE_NAME", "new third value"
                )
        )).containsExactly(
                "BIZBRAINZ_MONGODB_URI='new first value'",
                "BIZBRAINZ_REDIS_URL='second value'",
                "",
                "BIZBRAINZ_INSTANCE_NAME='new third value'"
        );

    }

    @Test
    public void emptyValues() {
        final String content = "BIZBRAINZ_MONGODB_URI=first value\nBIZBRAINZ_REDIS_URL=\n\nBIZBRAINZ_INSTANCE_NAME=third value";

        assertThat(envManager.transformEnvContent(
                content,
                Map.of("BIZBRAINZ_REDIS_URL", "new second value")
        )).containsExactly(
                "BIZBRAINZ_MONGODB_URI=first value",
                "BIZBRAINZ_REDIS_URL='new second value'",
                "",
                "BIZBRAINZ_INSTANCE_NAME=third value"
        );

        assertThat(envManager.transformEnvContent(
                content,
                Map.of("BIZBRAINZ_REDIS_URL", "")
        )).containsExactly(
                "BIZBRAINZ_MONGODB_URI=first value",
                "BIZBRAINZ_REDIS_URL=",
                "",
                "BIZBRAINZ_INSTANCE_NAME=third value"
        );

    }

    @Test
    public void quotedValues() {
        final String content = "BIZBRAINZ_MONGODB_URI='first value'\nBIZBRAINZ_REDIS_URL=\"quoted value\"\n\nBIZBRAINZ_INSTANCE_NAME='third value'";

        assertThat(envManager.transformEnvContent(
                content,
                Map.of(
                        "BIZBRAINZ_MONGODB_URI", "new first value",
                        "BIZBRAINZ_REDIS_URL", "new second value"
                )
        )).containsExactly(
                "BIZBRAINZ_MONGODB_URI='new first value'",
                "BIZBRAINZ_REDIS_URL='new second value'",
                "",
                "BIZBRAINZ_INSTANCE_NAME='third value'"
        );

        assertThat(envManager.transformEnvContent(
                content,
                Map.of("BIZBRAINZ_REDIS_URL", "")
        )).containsExactly(
                "BIZBRAINZ_MONGODB_URI='first value'",
                "BIZBRAINZ_REDIS_URL=",
                "",
                "BIZBRAINZ_INSTANCE_NAME='third value'"
        );

        assertThat(envManager.transformEnvContent(
                content,
                Map.of(
                        "BIZBRAINZ_INSTANCE_NAME", "Sponge-bob's Instance",
                        "BIZBRAINZ_REDIS_URL", "value with \" char in it"
                )
        )).containsExactly(
                "BIZBRAINZ_MONGODB_URI='first value'",
                "BIZBRAINZ_REDIS_URL='value with \" char in it'",
                "",
                "BIZBRAINZ_INSTANCE_NAME='Sponge-bob'\"'\"'s Instance'"
        );

    }

    @Test
    public void parseEmptyValues() {

        assertThat(envManager.parseToMap(
                "BIZBRAINZ_MONGODB_URI='first value'\nBIZBRAINZ_REDIS_URL=\n\nBIZBRAINZ_INSTANCE_NAME='third value'"
        )).containsExactlyInAnyOrderEntriesOf(Map.of(
                "BIZBRAINZ_MONGODB_URI", "first value",
                "BIZBRAINZ_REDIS_URL", "",
                "BIZBRAINZ_INSTANCE_NAME", "third value"
        ));

    }

    @Test
    public void parseQuotedValues() {

        assertThat(envManager.parseToMap(
                "BIZBRAINZ_MONGODB_URI=first\nBIZBRAINZ_REDIS_URL=\"quoted value\"\n\nBIZBRAINZ_INSTANCE_NAME='third value'"
        )).containsExactlyInAnyOrderEntriesOf(Map.of(
                "BIZBRAINZ_MONGODB_URI", "first",
                "BIZBRAINZ_REDIS_URL", "quoted value",
                "BIZBRAINZ_INSTANCE_NAME", "third value"
        ));

        assertThat(envManager.parseToMap(
                "BIZBRAINZ_INSTANCE_NAME=\"Sponge-bob's Instance\""
        )).containsExactlyInAnyOrderEntriesOf(Map.of(
                "BIZBRAINZ_INSTANCE_NAME", "Sponge-bob's Instance"
        ));

    }

    @Test
    public void parseTestWithEscapes() {
        assertThat(envManager.parseToMap(
                "BIZBRAINZ_ALLOWED_FRAME_ANCESTORS=\"'\"'none'\"'\"\nBIZBRAINZ_REDIS_URL='second\" value'\n"
        )).containsExactlyInAnyOrderEntriesOf(Map.of(
                "BIZBRAINZ_ALLOWED_FRAME_ANCESTORS", "'none'",
                "BIZBRAINZ_REDIS_URL", "second\" value"
        ));
    }

    @Test
    public void disallowedVariable() {
        final String content = "BIZBRAINZ_MONGODB_URI=first value\nDISALLOWED_NASTY_STUFF=\"quoted value\"\n\nBIZBRAINZ_INSTANCE_NAME=third value";

        assertThatThrownBy(() -> envManager.transformEnvContent(
                content,
                Map.of(
                        "BIZBRAINZ_MONGODB_URI", "new first value",
                        "DISALLOWED_NASTY_STUFF", "new second value"
                )
        ))
                .matches(value -> value instanceof BizbrainzException
                        && BizbrainzError.UNAUTHORIZED_ACCESS.equals(((BizbrainzException) value).getError()));
    }

    @Test
    public void addNewVariable() {
        final String content = "BIZBRAINZ_MONGODB_URI='first value'\nBIZBRAINZ_REDIS_URL='quoted value'\n\nBIZBRAINZ_INSTANCE_NAME='third value'";

        assertThat(envManager.transformEnvContent(
                content,
                Map.of(
                        "BIZBRAINZ_MONGODB_URI", "new first value",
                        "BIZBRAINZ_DISABLE_TELEMETRY", "false"
                )
        )).containsExactly(
                "BIZBRAINZ_MONGODB_URI='new first value'",
                "BIZBRAINZ_REDIS_URL='quoted value'",
                "",
                "BIZBRAINZ_INSTANCE_NAME='third value'",
                "BIZBRAINZ_DISABLE_TELEMETRY=false"
        );
    }

    @Test
    public void setValueWithQuotes() {
        final String content = "BIZBRAINZ_MONGODB_URI='first value'\nBIZBRAINZ_REDIS_URL='quoted value'\n\nBIZBRAINZ_INSTANCE_NAME='third value'";

        assertThat(envManager.transformEnvContent(
                content,
                Map.of(
                        "BIZBRAINZ_MONGODB_URI", "'just quotes'",
                        "BIZBRAINZ_DISABLE_TELEMETRY", "some quotes 'inside' it"
                )
        )).containsExactly(
                "BIZBRAINZ_MONGODB_URI=\"'\"'just quotes'\"'\"",
                "BIZBRAINZ_REDIS_URL='quoted value'",
                "",
                "BIZBRAINZ_INSTANCE_NAME='third value'",
                "BIZBRAINZ_DISABLE_TELEMETRY='some quotes '\"'\"'inside'\"'\"' it'"
        );
    }

    @Test
    public void download_UserIsNotSuperUser_ThrowsAccessDenied() {
        User user = new User();
        user.setEmail("sample-super-user");
        Mockito.when(sessionUserService.getCurrentUser()).thenReturn(Mono.just(user));
        Mockito.when(userService.findByEmail(user.getEmail())).thenReturn(Mono.just(user));
        Mockito.when(userUtils.isCurrentUserSuperUser()).thenReturn(Mono.just(false));

        ServerWebExchange exchange = Mockito.mock(ServerWebExchange.class);
        ServerHttpResponse response = Mockito.mock(ServerHttpResponse.class);
        HttpHeaders headers = new HttpHeaders();

        StepVerifier.create(envManager.download(exchange))
                .expectErrorMessage(BizbrainzError.UNAUTHORIZED_ACCESS.getMessage())
                .verify();
    }

    @Test
    public void download_UserIsSuperUser_ReturnsZip() throws IOException {
        User user = new User();
        user.setEmail("sample-super-user");
        Mockito.when(sessionUserService.getCurrentUser()).thenReturn(Mono.just(user));
        Mockito.when(userService.findByEmail(user.getEmail())).thenReturn(Mono.just(user));
        Mockito.when(userUtils.isCurrentUserSuperUser()).thenReturn(Mono.just(true));

        // create a temp file for docker env
        File file = File.createTempFile("envmanager-test-docker-file", "env");
        file.deleteOnExit();

        Mockito.when(commonConfig.getEnvFilePath()).thenReturn(file.getAbsolutePath());
        Mockito.when(fileUtils.createZip(any())).thenReturn(new byte[1024]);

        ServerWebExchange exchange = Mockito.mock(ServerWebExchange.class);
        ServerHttpResponse response = Mockito.mock(ServerHttpResponse.class);
        HttpHeaders headers = new HttpHeaders();
        Mockito.when(response.getHeaders()).thenReturn(headers);
        Mockito.when(exchange.getResponse()).thenReturn(response);
        Mockito.when(response.writeWith(any())).thenReturn(Mono.empty());

        StepVerifier.create(envManager.download(exchange))
                .verifyComplete();

        assertThat(headers.getContentType().toString()).isEqualTo("application/zip");
        assertThat(headers.getContentDisposition().toString()).containsIgnoringCase("bizBrainz-config.zip");
    }

    @Test
    public void sendTestEmail_WhenUserNotSuperUser_ThrowsException() {
        User user = new User();
        user.setEmail("sample-super-user");
        Mockito.when(sessionUserService.getCurrentUser()).thenReturn(Mono.just(user));
        Mockito.when(userService.findByEmail(user.getEmail())).thenReturn(Mono.just(user));
        Mockito.when(userUtils.isCurrentUserSuperUser()).thenReturn(Mono.just(false));

        StepVerifier.create(envManager.sendTestEmail(null))
                .expectErrorMessage(BizbrainzError.UNAUTHORIZED_ACCESS.getMessage())
                .verify();
    }
}
