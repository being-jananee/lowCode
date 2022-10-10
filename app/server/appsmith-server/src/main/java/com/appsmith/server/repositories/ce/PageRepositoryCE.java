package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.Page;
import com.bizBrainz.server.repositories.BaseRepository;
import com.bizBrainz.server.repositories.CustomPageRepository;
import reactor.core.publisher.Flux;

public interface PageRepositoryCE extends BaseRepository<Page, String>, CustomPageRepository {

    Flux<Page> findByApplicationId(String applicationId);

}
