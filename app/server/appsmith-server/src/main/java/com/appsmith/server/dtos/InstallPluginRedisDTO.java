package com.bizBrainz.server.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstallPluginRedisDTO {
    String workspaceId;
    PluginWorkspaceDTO pluginWorkspaceDTO;
}
