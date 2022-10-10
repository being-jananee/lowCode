package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.UserData;
import com.bizBrainz.server.repositories.BaseRepository;
import com.bizBrainz.server.repositories.CustomUserDataRepository;
import reactor.core.publisher.Mono;

public interface UserDataRepositoryCE extends BaseRepository<UserData, String>, CustomUserDataRepository {

    Mono<UserData> findByUserId(String userId);

}
