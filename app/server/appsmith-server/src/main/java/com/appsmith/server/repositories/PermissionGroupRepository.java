package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.PermissionGroupRepositoryCE;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionGroupRepository extends PermissionGroupRepositoryCE, CustomPermissionGroupRepository {

}
