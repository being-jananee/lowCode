package com.bizBrainz.server.services;

import com.bizBrainz.server.configurations.CommonConfig;
import com.bizBrainz.server.helpers.PolicyUtils;
import com.bizBrainz.server.helpers.UserUtils;
import com.bizBrainz.server.services.ce.AnalyticsServiceCEImpl;
import com.segment.analytics.Analytics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AnalyticsServiceImpl extends AnalyticsServiceCEImpl implements AnalyticsService {

    @Autowired
    public AnalyticsServiceImpl(@Autowired(required = false) Analytics analytics,
                                SessionUserService sessionUserService,
                                CommonConfig commonConfig,
                                ConfigService configService,
                                PolicyUtils policyUtils,
                                UserUtils userUtils) {

        super(analytics, sessionUserService, commonConfig, configService, policyUtils, userUtils);
    }


}
