package com.bizBrainz.server.services.ce;

import com.bizBrainz.server.domains.Comment;
import com.bizBrainz.server.domains.CommentThread;
import com.bizBrainz.server.domains.Notification;
import com.bizBrainz.server.dtos.UpdateIsReadNotificationByIdDTO;
import com.bizBrainz.server.dtos.UpdateIsReadNotificationDTO;
import com.bizBrainz.server.events.CommentNotificationEvent;
import com.bizBrainz.server.services.CrudService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NotificationServiceCE extends CrudService<Notification, String> {

    Mono<Notification> createNotification(Comment comment, CommentNotificationEvent event, String forUsername);

    Flux<Notification> createNotification(CommentThread commentThread, CommentNotificationEvent event, String authorUserName);

    Mono<UpdateIsReadNotificationByIdDTO> updateIsRead(UpdateIsReadNotificationByIdDTO dto);

    Mono<UpdateIsReadNotificationDTO> updateIsRead(UpdateIsReadNotificationDTO dto);

    Mono<Long> getUnreadCount();
}
