package com.bizBrainz.server.solutions;

import com.bizBrainz.server.configurations.EmailConfig;
import com.bizBrainz.server.helpers.PolicyUtils;
import com.bizBrainz.server.notifications.EmailSender;
import com.bizBrainz.server.repositories.ApplicationRepository;
import com.bizBrainz.server.repositories.NewPageRepository;
import com.bizBrainz.server.repositories.WorkspaceRepository;
import com.bizBrainz.server.services.UserWorkspaceService;
import com.bizBrainz.server.solutions.ce.EmailEventHandlerCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailEventHandlerImpl extends EmailEventHandlerCEImpl implements EmailEventHandler {


    public EmailEventHandlerImpl(ApplicationEventPublisher applicationEventPublisher, EmailSender emailSender,
                                 WorkspaceRepository workspaceRepository, ApplicationRepository applicationRepository,
                                 NewPageRepository newPageRepository, PolicyUtils policyUtils, EmailConfig emailConfig,
                                 UserWorkspaceService userWorkspaceService) {

        super(applicationEventPublisher, emailSender, workspaceRepository, applicationRepository, newPageRepository,
                policyUtils, emailConfig, userWorkspaceService);
    }
}
