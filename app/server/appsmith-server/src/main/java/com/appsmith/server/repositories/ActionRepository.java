package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.ActionRepositoryCE;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends ActionRepositoryCE, CustomActionRepository {

}
