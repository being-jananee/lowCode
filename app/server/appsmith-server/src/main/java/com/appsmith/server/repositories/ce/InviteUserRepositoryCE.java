package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.InviteUser;
import com.bizBrainz.server.repositories.BaseRepository;
import reactor.core.publisher.Mono;

public interface InviteUserRepositoryCE extends BaseRepository<InviteUser, String> {

    Mono<InviteUser> findByEmail(String email);
}
