package com.bizBrainz.server.services;

import com.bizBrainz.server.helpers.PolicyUtils;
import com.bizBrainz.server.notifications.EmailSender;
import com.bizBrainz.server.repositories.WorkspaceRepository;
import com.bizBrainz.server.repositories.UserDataRepository;
import com.bizBrainz.server.repositories.UserRepository;
import com.bizBrainz.server.services.ce.UserWorkspaceServiceCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserWorkspaceServiceImpl extends UserWorkspaceServiceCEImpl implements UserWorkspaceService {

    public UserWorkspaceServiceImpl(SessionUserService sessionUserService,
                                    WorkspaceRepository workspaceRepository,
                                    UserRepository userRepository,
                                    UserDataRepository userDataRepository,
                                    PolicyUtils policyUtils,
                                    EmailSender emailSender,
                                    UserDataService userDataService,
                                    PermissionGroupService permissionGroupService,
                                    TenantService tenantService) {

        super(sessionUserService, workspaceRepository, userRepository, userDataRepository, policyUtils, emailSender,
                userDataService, permissionGroupService, tenantService);
    }
}
