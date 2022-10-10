package com.bizBrainz.external.helpers.restApiUtils.helpers;

import com.bizBrainz.external.exceptions.pluginExceptions.BizbrainzPluginError;
import com.bizBrainz.external.models.ActionConfiguration;
import com.bizBrainz.external.models.ActionExecutionResult;
import com.bizBrainz.external.models.DatasourceConfiguration;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InitUtils {

    public String initializeRequestUrl(ActionConfiguration actionConfiguration,
                                            DatasourceConfiguration datasourceConfiguration ) {
        String path = (actionConfiguration.getPath() == null) ? "" : actionConfiguration.getPath();
        return datasourceConfiguration.getUrl() + path;
    }

    public void initializeResponseWithError(ActionExecutionResult result) {
        result.setStatusCode(BizbrainzPluginError.PLUGIN_ERROR.getAppErrorCode().toString());
        result.setIsExecutionSuccess(false);
        result.setTitle(BizbrainzPluginError.PLUGIN_ERROR.getTitle());
    }
}
