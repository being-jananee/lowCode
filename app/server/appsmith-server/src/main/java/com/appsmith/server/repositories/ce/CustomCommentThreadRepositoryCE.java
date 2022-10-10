package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.acl.AclPermission;
import com.bizBrainz.server.domains.ApplicationMode;
import com.bizBrainz.server.domains.CommentThread;
import com.bizBrainz.server.dtos.CommentThreadFilterDTO;
import com.bizBrainz.server.repositories.BizbrainzRepository;
import com.mongodb.client.result.UpdateResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface CustomCommentThreadRepositoryCE extends BizbrainzRepository<CommentThread> {

    Flux<CommentThread> findByApplicationId(String applicationId, AclPermission permission);

    Flux<CommentThread> find(CommentThreadFilterDTO commentThreadFilterDTO, AclPermission permission);

    Mono<UpdateResult> addToSubscribers(String threadId, Set<String> usernames);

    Mono<UpdateResult> removeSubscriber(String threadId, String username);

    Mono<CommentThread> findPrivateThread(String applicationId);

    Mono<Long> countUnreadThreads(String applicationId, String userEmail);

    Mono<UpdateResult> archiveByPageId(String pageId, ApplicationMode commentMode);
}
