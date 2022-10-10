package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.Notification;
import com.bizBrainz.server.repositories.BizbrainzRepository;
import com.mongodb.client.result.UpdateResult;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CustomNotificationRepositoryCE extends BizbrainzRepository<Notification> {
    Mono<UpdateResult> updateIsReadByForUsernameAndIdList(String forUsername, List<String> idList, boolean isRead);
    Mono<UpdateResult> updateIsReadByForUsername(String forUsername, boolean isRead);
    Mono<Void> updateCommentAuthorNames(String authorId, String authorName);
}
