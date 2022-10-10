package com.bizBrainz.external.dtos;

import com.bizBrainz.external.models.DatasourceConfiguration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DatasourceDTO {
    String id;
    DatasourceConfiguration datasourceConfiguration;
}
