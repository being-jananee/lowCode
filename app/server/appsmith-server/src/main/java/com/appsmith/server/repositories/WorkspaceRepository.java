package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.WorkspaceRepositoryCE;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends WorkspaceRepositoryCE, CustomWorkspaceRepository {

}
