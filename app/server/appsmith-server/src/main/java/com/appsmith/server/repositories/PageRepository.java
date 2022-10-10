package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.PageRepositoryCE;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends PageRepositoryCE, CustomPageRepository {

}
