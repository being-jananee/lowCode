package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.ActionCollectionRepositoryCE;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionCollectionRepository extends CustomActionCollectionRepository, ActionCollectionRepositoryCE {

}
