package com.bizBrainz.server.services.ce;

import com.bizBrainz.external.models.Datasource;
import com.bizBrainz.server.acl.AclPermission;
import com.bizBrainz.server.domains.Application;
import com.bizBrainz.server.domains.Config;
import com.bizBrainz.server.domains.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface ConfigServiceCE {

    Mono<Config> getByName(String name);

    Mono<Config> updateByName(Config config);

    Mono<Config> save(Config config);

    Mono<Config> save(String name, Map<String, Object> config);

    Mono<String> getInstanceId();

    Mono<String> getTemplateWorkspaceId();

    Flux<Application> getTemplateApplications();

    Flux<Datasource> getTemplateDatasources();

    Mono<Void> delete(String name);

    Mono<Config> getByName(String name, AclPermission permission);

    Mono<Config> getByNameAsUser(String name, User user, AclPermission permission);
}
