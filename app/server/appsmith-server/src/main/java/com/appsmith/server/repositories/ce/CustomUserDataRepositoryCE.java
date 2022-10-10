package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.UserData;
import com.bizBrainz.server.repositories.BizbrainzRepository;
import com.mongodb.client.result.UpdateResult;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CustomUserDataRepositoryCE extends BizbrainzRepository<UserData> {

    Mono<UpdateResult> saveReleaseNotesViewedVersion(String userId, String version);

    Mono<UpdateResult> removeIdFromRecentlyUsedList(String userId, String workspaceId, List<String> applicationIds);
}
