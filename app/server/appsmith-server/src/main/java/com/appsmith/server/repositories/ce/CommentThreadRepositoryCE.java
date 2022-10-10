package com.bizBrainz.server.repositories.ce;

import com.bizBrainz.server.domains.CommentThread;
import com.bizBrainz.server.repositories.BaseRepository;
import com.bizBrainz.server.repositories.CustomCommentThreadRepository;

public interface CommentThreadRepositoryCE extends BaseRepository<CommentThread, String>, CustomCommentThreadRepository {

}
