package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.NewActionRepositoryCE;
import org.springframework.stereotype.Repository;

@Repository
public interface NewActionRepository extends NewActionRepositoryCE, CustomNewActionRepository {

}
