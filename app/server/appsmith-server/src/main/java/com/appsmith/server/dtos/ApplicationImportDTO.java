package com.bizBrainz.server.dtos;

import com.bizBrainz.external.models.Datasource;
import com.bizBrainz.server.domains.Application;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApplicationImportDTO {
    Application application;

    List<Datasource> unConfiguredDatasourceList;

    Boolean isPartialImport;
}
