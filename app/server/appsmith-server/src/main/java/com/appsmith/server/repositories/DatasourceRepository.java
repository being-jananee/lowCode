package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.DatasourceRepositoryCE;
import org.springframework.stereotype.Repository;

@Repository
public interface DatasourceRepository extends DatasourceRepositoryCE, CustomDatasourceRepository {

}
