package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.UserDataRepositoryCE;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends UserDataRepositoryCE, CustomUserDataRepository {

}
