package com.bizBrainz.server.events;

import com.bizBrainz.server.domains.Application;
import com.bizBrainz.server.domains.Comment;
import com.bizBrainz.server.domains.Workspace;
import com.bizBrainz.server.dtos.UserAndPermissionGroupDTO;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class CommentAddedEvent extends AbstractCommentEvent {
    private final Comment comment;
    private final Set<String> subscribers;

    public CommentAddedEvent(Workspace workspace, List<UserAndPermissionGroupDTO> workspaceMembers, Application application,
                             String originHeader, Comment comment, Set<String> subscribers, String pageName) {
        super(comment.getAuthorUsername(), workspace, workspaceMembers, application, originHeader, pageName);
        this.comment = comment;
        this.subscribers = subscribers;
    }
}
