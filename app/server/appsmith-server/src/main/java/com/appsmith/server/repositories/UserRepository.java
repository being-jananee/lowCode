package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.UserRepositoryCE;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends UserRepositoryCE, CustomUserRepository {

}
