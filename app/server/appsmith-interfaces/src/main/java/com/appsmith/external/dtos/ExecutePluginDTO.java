package com.bizBrainz.external.dtos;

import com.bizBrainz.external.models.ActionConfiguration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExecutePluginDTO {
    String installationKey;
    String pluginName;
    String pluginVersion;
    String actionTemplateName;
    String datasourceTemplateName;
    DatasourceDTO datasource;
    ActionConfiguration actionConfiguration;
    ExecuteActionDTO executeActionDTO;
}
