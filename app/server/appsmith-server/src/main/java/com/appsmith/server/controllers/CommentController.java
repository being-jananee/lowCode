package com.bizBrainz.server.controllers;

import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.controllers.ce.CommentControllerCE;
import com.bizBrainz.server.services.CommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Url.COMMENT_URL)
public class CommentController extends CommentControllerCE {

    public CommentController(CommentService service) {
        super(service);
    }

}
