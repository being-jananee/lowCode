package com.bizBrainz.server.solutions;

import com.bizBrainz.server.repositories.CommentRepository;
import com.bizBrainz.server.repositories.NotificationRepository;
import com.bizBrainz.server.repositories.WorkspaceRepository;
import com.bizBrainz.server.solutions.ce.UserChangedHandlerCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserChangedHandlerImpl extends UserChangedHandlerCEImpl implements UserChangedHandler {

    public UserChangedHandlerImpl(ApplicationEventPublisher applicationEventPublisher,
                                  CommentRepository commentRepository,
                                  NotificationRepository notificationRepository,
                                  WorkspaceRepository workspaceRepository) {

        super(applicationEventPublisher, commentRepository, notificationRepository, workspaceRepository);
    }
}
