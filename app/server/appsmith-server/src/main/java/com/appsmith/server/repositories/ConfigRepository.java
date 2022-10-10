package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.ConfigRepositoryCE;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends ConfigRepositoryCE, CustomConfigRepository {

}
