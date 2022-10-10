package com.bizBrainz.server.events;

import com.bizBrainz.server.domains.Application;
import com.bizBrainz.server.domains.Workspace;
import com.bizBrainz.server.dtos.UserAndPermissionGroupDTO;
import lombok.Data;

import java.util.List;

@Data
public abstract class AbstractCommentEvent {
    private final String authorUserName;
    private final Workspace workspace;
    private final List<UserAndPermissionGroupDTO> workspaceMembers;
    private final Application application;
    private final String originHeader;
    private final String pageName;
}
