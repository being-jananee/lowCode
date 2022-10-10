package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.ApplicationRepositoryCE;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends ApplicationRepositoryCE, CustomApplicationRepository {

}
