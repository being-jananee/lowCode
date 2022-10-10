package com.bizBrainz.server.solutions;

import com.bizBrainz.server.repositories.DatasourceRepository;
import com.bizBrainz.server.repositories.NewPageRepository;
import com.bizBrainz.server.repositories.WorkspaceRepository;
import com.bizBrainz.server.services.ActionCollectionService;
import com.bizBrainz.server.services.ApplicationPageService;
import com.bizBrainz.server.services.ApplicationService;
import com.bizBrainz.server.services.ConfigService;
import com.bizBrainz.server.services.DatasourceService;
import com.bizBrainz.server.services.LayoutActionService;
import com.bizBrainz.server.services.LayoutCollectionService;
import com.bizBrainz.server.services.NewActionService;
import com.bizBrainz.server.services.WorkspaceService;
import com.bizBrainz.server.services.SessionUserService;
import com.bizBrainz.server.services.ThemeService;
import com.bizBrainz.server.services.UserService;
import com.bizBrainz.server.solutions.ce.ExamplesWorkspaceClonerCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExamplesWorkspaceClonerImpl extends ExamplesWorkspaceClonerCEImpl implements ExamplesWorkspaceCloner {

    public ExamplesWorkspaceClonerImpl(WorkspaceService workspaceService,
                                          WorkspaceRepository workspaceRepository,
                                          DatasourceService datasourceService,
                                          DatasourceRepository datasourceRepository,
                                          ConfigService configService,
                                          SessionUserService sessionUserService,
                                          UserService userService,
                                          ApplicationService applicationService,
                                          ApplicationPageService applicationPageService,
                                          NewPageRepository newPageRepository,
                                          NewActionService newActionService,
                                          LayoutActionService layoutActionService,
                                          ActionCollectionService actionCollectionService,
                                          LayoutCollectionService layoutCollectionService,
                                          ThemeService themeService) {

        super(workspaceService, workspaceRepository, datasourceService, datasourceRepository, configService,
                sessionUserService, userService, applicationService, applicationPageService, newPageRepository,
                newActionService, layoutActionService, actionCollectionService, layoutCollectionService, themeService);
    }
}
