package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.Comment;
import com.bizBrainz.server.repositories.BizbrainzRepository;
import com.mongodb.client.result.UpdateResult;
import reactor.core.publisher.Mono;

public interface CustomCommentRepositoryCE extends BizbrainzRepository<Comment> {

    Mono<UpdateResult> pushReaction(String commentId, Comment.Reaction reaction);

    Mono<UpdateResult> deleteReaction(String commentId, Comment.Reaction reaction);

    Mono<Void> updateAuthorNames(String authorId, String authorName);

    Mono<Void> updatePhotoId(String authorId, String photoId);
}
