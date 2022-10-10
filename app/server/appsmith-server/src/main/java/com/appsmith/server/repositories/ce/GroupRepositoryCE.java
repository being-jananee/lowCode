package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.Group;
import com.bizBrainz.server.repositories.BaseRepository;
import com.bizBrainz.server.repositories.CustomGroupRepository;

public interface GroupRepositoryCE extends BaseRepository<Group, String>, CustomGroupRepository {

}
