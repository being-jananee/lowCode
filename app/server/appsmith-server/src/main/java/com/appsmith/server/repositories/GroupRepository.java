package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.GroupRepositoryCE;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends GroupRepositoryCE, CustomGroupRepository {

}
