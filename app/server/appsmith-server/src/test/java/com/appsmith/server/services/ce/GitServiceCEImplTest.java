package com.bizBrainz.server.services.ce;

import com.bizBrainz.external.git.GitExecutor;
import com.bizBrainz.server.configurations.EmailConfig;
import com.bizBrainz.server.helpers.GitCloudServicesUtils;
import com.bizBrainz.server.helpers.GitFileUtils;
import com.bizBrainz.server.helpers.ResponseUtils;
import com.bizBrainz.server.repositories.GitDeployKeysRepository;
import com.bizBrainz.server.services.ActionCollectionService;
import com.bizBrainz.server.services.AnalyticsService;
import com.bizBrainz.server.services.ApplicationPageService;
import com.bizBrainz.server.services.ApplicationService;
import com.bizBrainz.server.services.DatasourceService;
import com.bizBrainz.server.services.NewActionService;
import com.bizBrainz.server.services.NewPageService;
import com.bizBrainz.server.services.PluginService;
import com.bizBrainz.server.services.SessionUserService;
import com.bizBrainz.server.services.UserDataService;
import com.bizBrainz.server.services.UserService;
import com.bizBrainz.server.solutions.ImportExportApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;


@ExtendWith(SpringExtension.class)
@Slf4j
public class GitServiceCEImplTest {

    GitServiceCE gitService;

    @MockBean
    AnalyticsService analyticsService;
    @MockBean
    DatasourceService datasourceService;
    @MockBean
    PluginService pluginService;
    @MockBean
    NewPageService newPageService;
    @MockBean
    ApplicationService applicationService;
    @MockBean
    SessionUserService sessionUserService;
    @MockBean
    ResponseUtils responseUtils;
    @MockBean
    UserService userService;
    @MockBean
    UserDataService userDataService;
    @MockBean
    ApplicationPageService applicationPageService;
    @MockBean
    NewActionService newActionService;
    @MockBean
    ActionCollectionService actionCollectionService;
    @MockBean
    GitFileUtils gitFileUtils;
    @MockBean
    ImportExportApplicationService importExportApplicationService;
    @MockBean
    GitExecutor gitExecutor;
    @MockBean
    EmailConfig emailConfig;
    @MockBean
    GitCloudServicesUtils gitCloudServicesUtils;
    @MockBean
    GitDeployKeysRepository gitDeployKeysRepository;

    @BeforeEach
    public void setup() {
        gitService = new GitServiceCEImpl(
                userService, userDataService, sessionUserService, applicationService, applicationPageService,
                newPageService, newActionService, actionCollectionService, gitFileUtils, importExportApplicationService,
                gitExecutor, responseUtils, emailConfig, analyticsService, gitCloudServicesUtils, gitDeployKeysRepository,
                datasourceService, pluginService
        );
    }

    @Test
    public void isRepoLimitReached_connectedAppCountIsLessThanLimit_Success() {

        doReturn(Mono.just(3))
                .when(gitCloudServicesUtils).getPrivateRepoLimitForOrg(Mockito.any(String.class), Mockito.any(Boolean.class));

        GitServiceCE gitService1 = Mockito.spy(gitService);
        doReturn(Mono.just(1L))
                .when(gitService1).getApplicationCountWithPrivateRepo(Mockito.any(String.class));

        StepVerifier
                .create(gitService1.isRepoLimitReached("workspaceId", false))
                .assertNext(aBoolean -> assertEquals(false, aBoolean))
                .verifyComplete();
    }

    @Test
    public void isRepoLimitReached_connectedAppCountIsSameAsLimit_Success() {
        doReturn(Mono.just(3))
                .when(gitCloudServicesUtils).getPrivateRepoLimitForOrg(Mockito.any(String.class), Mockito.any(Boolean.class));

        GitServiceCE gitService1 = Mockito.spy(gitService);
        doReturn(Mono.just(3L))
                .when(gitService1).getApplicationCountWithPrivateRepo(Mockito.any(String.class));

        StepVerifier
                .create(gitService1.isRepoLimitReached("workspaceId", false))
                .assertNext(aBoolean -> assertEquals(true, aBoolean))
                .verifyComplete();
    }
}