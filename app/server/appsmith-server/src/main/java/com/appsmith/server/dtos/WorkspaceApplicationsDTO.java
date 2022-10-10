package com.bizBrainz.server.dtos;

import com.bizBrainz.server.domains.Application;
import com.bizBrainz.server.domains.Workspace;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class WorkspaceApplicationsDTO {
    Workspace workspace;
    List<Application> applications;
    List<UserAndPermissionGroupDTO> users;
}
