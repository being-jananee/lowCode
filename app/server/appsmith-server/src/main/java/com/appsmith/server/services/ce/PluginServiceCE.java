package com.bizBrainz.server.services.ce;

import com.bizBrainz.external.models.Datasource;
import com.bizBrainz.server.domains.Workspace;
import com.bizBrainz.server.domains.Plugin;
import com.bizBrainz.server.dtos.InstallPluginRedisDTO;
import com.bizBrainz.server.dtos.PluginWorkspaceDTO;
import com.bizBrainz.server.services.CrudService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface PluginServiceCE extends CrudService<Plugin, String> {

    Flux<Plugin> getDefaultPlugins();

    Flux<Plugin> getDefaultPluginIcons();

    Mono<Workspace> installPlugin(PluginWorkspaceDTO plugin);

    Flux<Workspace> installDefaultPlugins(List<Plugin> plugins);

    Mono<Workspace> uninstallPlugin(PluginWorkspaceDTO plugin);

    Mono<Plugin> findByName(String name);

    Mono<Plugin> findByPackageName(String packageName);

    Mono<Plugin> findById(String id);

    Mono<String> getPluginName(Mono<Datasource> datasourceMono);

    Plugin redisInstallPlugin(InstallPluginRedisDTO installPluginRedisDTO);

    Mono<Map> getFormConfig(String pluginId);

    Flux<Plugin> getAllRemotePlugins();

    Mono<Map> loadPluginResource(String pluginId, String resourcePath);

    Mono<Map> getEditorConfigLabelMap(String pluginId);

    Map loadEditorPluginResourceUqi(Plugin plugin);

    Flux<Plugin> saveAll(Iterable<Plugin> plugins);
}
