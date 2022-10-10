package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.PluginRepositoryCE;
import org.springframework.stereotype.Repository;

@Repository
public interface PluginRepository extends PluginRepositoryCE, CustomPluginRepository {

}
