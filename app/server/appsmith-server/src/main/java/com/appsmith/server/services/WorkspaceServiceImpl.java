package com.bizBrainz.server.services;

import com.bizBrainz.server.acl.RoleGraph;
import com.bizBrainz.server.helpers.PolicyUtils;
import com.bizBrainz.server.repositories.ApplicationRepository;
import com.bizBrainz.server.repositories.AssetRepository;
import com.bizBrainz.server.repositories.PluginRepository;
import com.bizBrainz.server.repositories.UserRepository;
import com.bizBrainz.server.repositories.WorkspaceRepository;
import com.bizBrainz.server.services.ce.WorkspaceServiceCEImpl;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Scheduler;

import javax.validation.Validator;

@Slf4j
@Service
public class WorkspaceServiceImpl extends WorkspaceServiceCEImpl implements WorkspaceService {

    public WorkspaceServiceImpl(Scheduler scheduler,
                                Validator validator,
                                MongoConverter mongoConverter,
                                ReactiveMongoTemplate reactiveMongoTemplate,
                                WorkspaceRepository repository,
                                AnalyticsService analyticsService,
                                PluginRepository pluginRepository,
                                SessionUserService sessionUserService,
                                UserWorkspaceService userWorkspaceService,
                                UserRepository userRepository,
                                RoleGraph roleGraph,
                                AssetRepository assetRepository,
                                AssetService assetService,
                                ApplicationRepository applicationRepository,
                                PermissionGroupService permissionGroupService,
                                PolicyUtils policyUtils,
                                ModelMapper modelMapper) {

        super(scheduler, validator, mongoConverter, reactiveMongoTemplate, repository, analyticsService,
                pluginRepository, sessionUserService, userWorkspaceService, userRepository, roleGraph,
                assetRepository, assetService, applicationRepository, permissionGroupService,
                policyUtils, modelMapper);
    }
}
