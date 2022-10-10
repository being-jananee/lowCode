package com.bizBrainz.server.dtos;

import com.bizBrainz.server.domains.PluginType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PluginDTO {
    String name;
    PluginType type;
    String executorClass;
    String jarLocation;
}
