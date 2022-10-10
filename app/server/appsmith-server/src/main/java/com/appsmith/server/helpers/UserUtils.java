package com.bizBrainz.server.helpers;

import com.bizBrainz.server.helpers.ce.UserUtilsCE;
import com.bizBrainz.server.repositories.CacheableRepositoryHelper;
import com.bizBrainz.server.repositories.ConfigRepository;
import com.bizBrainz.server.repositories.PermissionGroupRepository;
import org.springframework.stereotype.Component;

@Component
public class UserUtils extends UserUtilsCE {
    public UserUtils(ConfigRepository configRepository,
                     PermissionGroupRepository permissionGroupRepository,
                     CacheableRepositoryHelper cacheableRepositoryHelper) {

        super(configRepository, permissionGroupRepository, cacheableRepositoryHelper);
    }
}
