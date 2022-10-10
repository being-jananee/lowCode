package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.acl.AclPermission;
import com.bizBrainz.server.domains.Page;
import com.bizBrainz.server.repositories.BizbrainzRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomPageRepositoryCE extends BizbrainzRepository<Page> {
    Mono<Page> findByIdAndLayoutsId(String id, String layoutId, AclPermission aclPermission);

    Mono<Page> findByName(String name, AclPermission aclPermission);

    Flux<Page> findByApplicationId(String applicationId, AclPermission aclPermission);

    Mono<Page> findByNameAndApplicationId(String name, String applicationId, AclPermission aclPermission);
}
