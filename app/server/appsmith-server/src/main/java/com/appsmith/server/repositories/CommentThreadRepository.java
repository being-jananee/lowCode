package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.CommentThreadRepositoryCE;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentThreadRepository extends CommentThreadRepositoryCE, CustomCommentThreadRepository {

}
