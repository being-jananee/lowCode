package com.bizBrainz.server.repositories;

import com.bizBrainz.server.repositories.ce.CommentRepositoryCE;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CommentRepositoryCE, CustomCommentRepository {

}
