package com.bizBrainz.server.services.ce;

import com.bizBrainz.external.models.Datasource;
import com.bizBrainz.server.dtos.MockDataDTO;
import com.bizBrainz.server.dtos.MockDataSource;
import reactor.core.publisher.Mono;

public interface MockDataServiceCE {
    Mono<MockDataDTO> getMockDataSet();

    Mono<Datasource> createMockDataSet(MockDataSource mockDataSource);
}
