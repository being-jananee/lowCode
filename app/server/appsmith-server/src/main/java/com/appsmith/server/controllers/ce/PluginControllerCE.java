package com.bizBrainz.server.controllers.ce;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.domains.Workspace;
import com.bizBrainz.server.domains.Plugin;
import com.bizBrainz.server.dtos.PluginWorkspaceDTO;
import com.bizBrainz.server.dtos.ResponseDTO;
import com.bizBrainz.server.services.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;


@RequestMapping(Url.PLUGIN_URL)
public class PluginControllerCE extends BaseController<PluginService, Plugin, String> {

    @Autowired
    public PluginControllerCE(PluginService service) {
        super(service);
    }

    @PostMapping("/install")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseDTO<Workspace>> install(@Valid @RequestBody PluginWorkspaceDTO plugin) {
        return service.installPlugin(plugin)
                .map(workspace -> new ResponseDTO<>(HttpStatus.CREATED.value(), workspace, null));
    }

    @PostMapping("/uninstall")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseDTO<Workspace>> uninstall(@Valid @RequestBody PluginWorkspaceDTO plugin) {
        return service.uninstallPlugin(plugin)
                .map(workspace -> new ResponseDTO<>(HttpStatus.CREATED.value(), workspace, null));
    }

    @GetMapping("/{pluginId}/form")
    public Mono<ResponseDTO<Object>> getDatasourceForm(@PathVariable String pluginId) {
        return service.getFormConfig(pluginId)
                .map(form -> new ResponseDTO<>(HttpStatus.OK.value(), form, null));
    }

    @GetMapping("/default/icons")
    public Mono<ResponseDTO<List<Plugin>>> getDefaultPluginIcons() {
        return service.getDefaultPluginIcons()
                .collectList()
                .map(data -> new ResponseDTO<>(HttpStatus.OK.value(), data, null));
    }
}
