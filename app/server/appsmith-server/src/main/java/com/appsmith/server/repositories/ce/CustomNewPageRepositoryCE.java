package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.acl.AclPermission;
import com.bizBrainz.server.domains.NewPage;
import com.bizBrainz.server.repositories.BizbrainzRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CustomNewPageRepositoryCE extends BizbrainzRepository<NewPage> {

    Flux<NewPage> findByApplicationId(String applicationId, AclPermission aclPermission);

    Flux<NewPage> findByApplicationIdAndNonDeletedEditMode(String applicationId, AclPermission aclPermission);

    Mono<NewPage> findByIdAndLayoutsIdAndViewMode(String id, String layoutId, AclPermission aclPermission, Boolean viewMode);

    Mono<NewPage> findByNameAndViewMode(String name, AclPermission aclPermission, Boolean viewMode);

    Mono<NewPage> findByNameAndApplicationIdAndViewMode(String name, String applicationId, AclPermission aclPermission, Boolean viewMode);

    Flux<NewPage> findAllByIds(List<String> ids, AclPermission aclPermission);

    Mono<String> getNameByPageId(String pageId, boolean isPublishedName);

    Mono<NewPage> findPageByBranchNameAndDefaultPageId(String branchName, String defaultPageId, AclPermission permission);

    Flux<NewPage> findSlugsByApplicationIds(List<String> applicationIds, AclPermission aclPermission);
}
