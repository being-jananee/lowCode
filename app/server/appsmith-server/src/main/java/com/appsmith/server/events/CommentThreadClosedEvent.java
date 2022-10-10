package com.bizBrainz.server.events;

import com.bizBrainz.server.domains.Application;
import com.bizBrainz.server.domains.CommentThread;
import com.bizBrainz.server.domains.Workspace;
import com.bizBrainz.server.dtos.UserAndPermissionGroupDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class CommentThreadClosedEvent extends AbstractCommentEvent {
    private final CommentThread commentThread;

    public CommentThreadClosedEvent(String authorUserName, Workspace workspace, List<UserAndPermissionGroupDTO> workspaceMembers,
                                    Application application, String originHeader, CommentThread commentThread,
                                    String pagename) {

        super(authorUserName, workspace, workspaceMembers, application, originHeader, pagename);
        this.commentThread = commentThread;
    }
}
