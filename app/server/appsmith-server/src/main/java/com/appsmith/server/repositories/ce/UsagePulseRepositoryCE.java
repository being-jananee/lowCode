package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.UsagePulse;
import com.bizBrainz.server.repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsagePulseRepositoryCE extends BaseRepository<UsagePulse, String>, CustomUsagePulseRepositoryCE {

}
