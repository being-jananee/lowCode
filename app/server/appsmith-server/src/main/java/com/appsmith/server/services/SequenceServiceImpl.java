package com.bizBrainz.server.services;

import com.bizBrainz.server.services.ce.SequenceServiceCEImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class SequenceServiceImpl extends SequenceServiceCEImpl implements SequenceService {

    @Autowired
    public SequenceServiceImpl(ReactiveMongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

}
