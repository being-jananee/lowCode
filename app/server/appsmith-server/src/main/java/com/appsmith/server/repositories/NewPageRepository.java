package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.NewPageRepositoryCE;
import org.springframework.stereotype.Repository;

@Repository
public interface NewPageRepository extends NewPageRepositoryCE, CustomNewPageRepository {

}
