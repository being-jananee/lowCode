package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.UsagePulseRepositoryCE;
import org.springframework.stereotype.Repository;

@Repository
public interface UsagePulseRepository extends UsagePulseRepositoryCE, CustomUsagePulseRepository {
}
