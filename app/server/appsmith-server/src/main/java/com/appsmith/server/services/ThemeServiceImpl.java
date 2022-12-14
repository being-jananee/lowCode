package com.bizBrainz.server.services;

import com.bizBrainz.server.acl.PolicyGenerator;
import com.bizBrainz.server.repositories.ApplicationRepository;
import com.bizBrainz.server.repositories.ThemeRepository;
import com.bizBrainz.server.services.ce.ThemeServiceCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Scheduler;

import javax.validation.Validator;

@Slf4j
@Service
public class ThemeServiceImpl extends ThemeServiceCEImpl implements ThemeService {
    public ThemeServiceImpl(Scheduler scheduler, Validator validator, MongoConverter mongoConverter, ReactiveMongoTemplate reactiveMongoTemplate, ThemeRepository repository, AnalyticsService analyticsService, ApplicationRepository applicationRepository, ApplicationService applicationService, PolicyGenerator policyGenerator) {
        super(scheduler, validator, mongoConverter, reactiveMongoTemplate, repository, analyticsService, applicationRepository, applicationService, policyGenerator);
    }
}
