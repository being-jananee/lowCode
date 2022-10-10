package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.DatasourceControllerCE;
import com.bizBrainz.server.services.DatasourceService;
import com.bizBrainz.server.services.MockDataService;
import com.bizBrainz.server.solutions.AuthenticationService;
import com.bizBrainz.server.solutions.DatasourceStructureSolution;
import com.bizBrainz.server.solutions.DatasourceTriggerSolution;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Url.DATASOURCE_URL)
public class DatasourceController extends DatasourceControllerCE {

    public DatasourceController(DatasourceService service,
                                DatasourceStructureSolution datasourceStructureSolution,
                                AuthenticationService authenticationService,
                                MockDataService datasourceService,
                                DatasourceTriggerSolution datasourceTriggerSolution) {

        super(service, datasourceStructureSolution, authenticationService, datasourceService, datasourceTriggerSolution);
    }
}
