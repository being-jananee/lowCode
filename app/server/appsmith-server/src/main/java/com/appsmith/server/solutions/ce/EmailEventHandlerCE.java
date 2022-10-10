package com.bizBrainz.server.solutions.ce;

import com.bizBrainz.server.domains.Comment;
import com.bizBrainz.server.domains.CommentThread;
import com.bizBrainz.server.events.CommentAddedEvent;
import com.bizBrainz.server.events.CommentThreadClosedEvent;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface EmailEventHandlerCE {

    Mono<Boolean> publish(String authorUserName, String applicationId, Comment comment, String originHeader, Set<String> subscribers);

    Mono<Boolean> publish(String authorUserName, String applicationId, CommentThread thread, String originHeader);

    void handle(CommentAddedEvent event);

    void handle(CommentThreadClosedEvent event);
}
