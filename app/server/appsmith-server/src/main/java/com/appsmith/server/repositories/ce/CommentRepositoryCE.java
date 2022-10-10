package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.Comment;
import com.bizBrainz.server.repositories.BaseRepository;
import com.bizBrainz.server.repositories.CustomCommentRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface CommentRepositoryCE extends BaseRepository<Comment, String>, CustomCommentRepository {

    Flux<Comment> findByThreadIdInOrderByCreatedAt(List<String> threadIds);

}
