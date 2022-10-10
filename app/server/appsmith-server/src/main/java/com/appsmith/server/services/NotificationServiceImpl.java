package com.bizBrainz.server.services;

import com.bizBrainz.server.helpers.ResponseUtils;
import com.bizBrainz.server.repositories.NotificationRepository;
import com.bizBrainz.server.services.ce.NotificationServiceCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Scheduler;

import javax.validation.Validator;

@Slf4j
@Service
public class NotificationServiceImpl extends NotificationServiceCEImpl implements NotificationService {

    public NotificationServiceImpl(Scheduler scheduler,
                                   Validator validator,
                                   MongoConverter mongoConverter,
                                   ReactiveMongoTemplate reactiveMongoTemplate,
                                   NotificationRepository repository,
                                   AnalyticsService analyticsService,
                                   SessionUserService sessionUserService,
                                   ResponseUtils responseUtils) {

        super(scheduler, validator, mongoConverter, reactiveMongoTemplate, repository, analyticsService,
                sessionUserService, responseUtils);
    }
}
