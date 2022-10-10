package com.bizBrainz.server.solutions.ce;

import com.bizBrainz.external.models.Datasource;
import com.bizBrainz.server.constants.SerialiseApplicationObjective;
import com.bizBrainz.server.domains.Application;
import com.bizBrainz.server.dtos.ApplicationImportDTO;
import com.bizBrainz.server.dtos.ApplicationJson;
import com.bizBrainz.server.dtos.ExportFileDTO;
import org.springframework.http.codec.multipart.Part;
import reactor.core.publisher.Mono;

import java.util.List;


public interface ImportExportApplicationServiceCE {

    /**
     * This function will give the application resource to rebuild the application in import application flow
     *
     * @param applicationId which needs to be exported
     * @return application reference from which entire application can be rehydrated
     */
    Mono<ApplicationJson> exportApplicationById(String applicationId, SerialiseApplicationObjective serialiseFor);

    Mono<ApplicationJson> exportApplicationById(String applicationId, String branchName);

    Mono<ExportFileDTO> getApplicationFile(String applicationId, String branchName);

    /**
     * This function will take the Json filepart and saves the application in workspace
     *
     * @param workspaceId    workspace to which the application needs to be hydrated
     * @param filePart Json file which contains the entire application object
     * @return saved application in DB
     */
    Mono<ApplicationImportDTO> extractFileAndSaveApplication(String workspaceId, Part filePart);

    Mono<Application> mergeApplicationJsonWithApplication(String organizationId, String applicationId, String branchName, ApplicationJson applicationJson, List<String> pagesToImport);

    /**
     * This function will save the application to workspace from the application resource
     *
     * @param workspaceId workspace to which application is going to be stored
     * @param importedDoc    application resource which contains necessary information to save the application
     * @return saved application in DB
     */
    Mono<Application> importApplicationInWorkspace(String workspaceId, ApplicationJson importedDoc);

    /**
     * This function will take the application reference object to hydrate the application in mongoDB
     *
     * @param workspaceId workspace to which application is going to be stored
     * @param importedDoc    application resource which contains necessary information to save the application
     * @param applicationId  application which needs to be saved with the updated resources
     * @return Updated application
     */
    Mono<Application> importApplicationInWorkspace(String workspaceId,
                                                      ApplicationJson importedDoc,
                                                      String applicationId,
                                                      String branchName);

    Mono<List<Datasource>> findDatasourceByApplicationId(String applicationId, String orgId);

    Mono<ApplicationImportDTO> getApplicationImportDTO(String applicationId, String workspaceId, Application application);

}
