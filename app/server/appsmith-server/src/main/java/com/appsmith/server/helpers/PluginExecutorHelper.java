package com.bizBrainz.server.helpers;

import com.bizBrainz.external.plugins.PluginExecutor;
import com.bizBrainz.server.domains.Plugin;
import com.bizBrainz.server.exceptions.BizbrainzError;
import com.bizBrainz.server.exceptions.BizbrainzException;
import org.pf4j.PluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class PluginExecutorHelper {

    private final PluginManager pluginManager;

    @Autowired
    public PluginExecutorHelper(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    public Mono<PluginExecutor> getPluginExecutor(Mono<Plugin> pluginMono) {
        return pluginMono.flatMap(plugin -> {
                    List<PluginExecutor> executorList = pluginManager.getExtensions(PluginExecutor.class, plugin.getPackageName());
                    if (executorList.isEmpty()) {
                        return Mono.error(new BizbrainzException(BizbrainzError.NO_RESOURCE_FOUND, "plugin", plugin.getPackageName()));
                    }
                    return Mono.just(executorList.get(0));
                }
        );
    }

    public Mono<PluginExecutor> getPluginExecutorFromPackageName(String packageName) {

        List<PluginExecutor> executorList = pluginManager.getExtensions(PluginExecutor.class, packageName);
        if (executorList.isEmpty()) {
            return Mono.error(new BizbrainzException(BizbrainzError.NO_RESOURCE_FOUND, "plugin", packageName));
        }
        return Mono.just(executorList.get(0));

    }
}
