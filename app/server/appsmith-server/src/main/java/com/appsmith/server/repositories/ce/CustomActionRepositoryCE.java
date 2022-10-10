package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.acl.AclPermission;
import com.bizBrainz.server.domains.Action;
import com.bizBrainz.server.repositories.BizbrainzRepository;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

public interface CustomActionRepositoryCE extends BizbrainzRepository<Action> {

    Mono<Action> findByNameAndPageId(String name, String pageId, AclPermission aclPermission);

    Flux<Action> findByPageId(String pageId, AclPermission aclPermission);

    Flux<Action> findActionsByNameInAndPageIdAndActionConfiguration_HttpMethod(Set<String> names,
                                                                               String pageId,
                                                                               String httpMethod,
                                                                               AclPermission aclPermission);

    Flux<Action> findAllActionsByNameAndPageIds(String name, List<String> pageIds, AclPermission aclPermission, Sort sort);
}
