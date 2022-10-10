package com.bizBrainz.server.solutions;

import com.bizBrainz.server.acl.BizbrainzRole;
import com.bizBrainz.server.configurations.EmailConfig;
import com.bizBrainz.server.domains.Application;
import com.bizBrainz.server.domains.Comment;
import com.bizBrainz.server.domains.NewPage;
import com.bizBrainz.server.domains.UserRole;
import com.bizBrainz.server.domains.Workspace;
import com.bizBrainz.server.dtos.PageDTO;
import com.bizBrainz.server.helpers.PolicyUtils;
import com.bizBrainz.server.notifications.EmailSender;
import com.bizBrainz.server.repositories.ApplicationRepository;
import com.bizBrainz.server.repositories.NewPageRepository;
import com.bizBrainz.server.repositories.WorkspaceRepository;
import com.bizBrainz.server.services.UserWorkspaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
@ComponentScan("com.bizBrainz.server.solutions")
public class EmailEventHandlerTest {

    private static final String COMMENT_ADDED_EMAIL_TEMPLATE = "email/commentAddedTemplate.html";

    @MockBean
    private ApplicationEventPublisher applicationEventPublisher;
    @MockBean
    private EmailSender emailSender;
    @MockBean
    private WorkspaceRepository workspaceRepository;
    @MockBean
    private ApplicationRepository applicationRepository;
    @MockBean
    private NewPageRepository newPageRepository;
    @MockBean
    private EmailConfig emailConfig;
    @MockBean
    private PolicyUtils policyUtils;
    @MockBean
    UserWorkspaceService userWorkspaceService;

    EmailEventHandler emailEventHandler;

    private Application application;
    private Workspace workspace;

    String authorUserName = "abc";
    String originHeader = "efg";
    String applicationId = "application-id";
    String workspaceId = "workspace-id";
    String emailReceiverUsername = "email-receiver";

    @BeforeEach
    public void setUp() {

        emailEventHandler = new EmailEventHandlerImpl(applicationEventPublisher, emailSender, workspaceRepository,
                applicationRepository, newPageRepository, policyUtils, emailConfig, userWorkspaceService);

        application = new Application();
        application.setName("Test application for comment");
        application.setWorkspaceId(workspaceId);
        workspace = new Workspace();

        // add a role with email receiver username
        UserRole userRole = new UserRole();
        userRole.setUsername(emailReceiverUsername);
        userRole.setRole(BizbrainzRole.ORGANIZATION_ADMIN);
        workspace.setUserRoles(List.of(userRole));

        Mockito.when(applicationRepository.findById(applicationId)).thenReturn(Mono.just(application));
        Mockito.when(workspaceRepository.findById(workspaceId)).thenReturn(Mono.just(workspace));

        NewPage newPage = new NewPage();
        newPage.setUnpublishedPage(new PageDTO());
        newPage.getUnpublishedPage().setName("Page1");
        Mockito.when(newPageRepository.findById(anyString())).thenReturn(Mono.just(newPage));
    }

    @Test
    public void publish_CommentProvidedSubscriberIsNull_ReturnsFalse() {
        Mono<Boolean> booleanMono = emailEventHandler.publish(
                authorUserName, applicationId, new Comment(), originHeader, null
        );
        StepVerifier.create(booleanMono).assertNext(aBoolean -> {
            assertEquals(Boolean.FALSE, aBoolean);
        }).verifyComplete();
    }

    @Test
    public void publish_CommentProvidedSubscriberIsEmpty_ReturnsFalse() {
        Mono<Boolean> booleanMono = emailEventHandler.publish(
                authorUserName, applicationId, new Comment(), originHeader, Set.of()
        );
        StepVerifier.create(booleanMono).assertNext(aBoolean -> {
            assertEquals(Boolean.FALSE, aBoolean);
        }).verifyComplete();
    }
}