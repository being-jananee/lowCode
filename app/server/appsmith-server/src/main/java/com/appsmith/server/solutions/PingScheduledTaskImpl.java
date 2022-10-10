package com.bizBrainz.server.solutions;

import com.bizBrainz.server.configurations.CommonConfig;
import com.bizBrainz.server.configurations.SegmentConfig;
import com.bizBrainz.server.repositories.ApplicationRepository;
import com.bizBrainz.server.repositories.DatasourceRepository;
import com.bizBrainz.server.repositories.NewActionRepository;
import com.bizBrainz.server.repositories.NewPageRepository;
import com.bizBrainz.server.repositories.WorkspaceRepository;
import com.bizBrainz.server.repositories.UserRepository;
import com.bizBrainz.server.services.ConfigService;
import com.bizBrainz.server.solutions.ce.PingScheduledTaskCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * This class represents a scheduled task that pings a data point indicating that this server installation is live.
 * This ping is only invoked if the Bizbrainz server is NOT running in Bizbrainz Clouud & the user has given Bizbrainz
 * permissions to collect anonymized data
 */
@ConditionalOnExpression("!${is.cloud-hosted:false}")
@Slf4j
@Component
public class PingScheduledTaskImpl extends PingScheduledTaskCEImpl implements PingScheduledTask {

    public PingScheduledTaskImpl(
            ConfigService configService,
            SegmentConfig segmentConfig,
            CommonConfig commonConfig,
            WorkspaceRepository workspaceRepository,
            ApplicationRepository applicationRepository,
            NewPageRepository newPageRepository,
            NewActionRepository newActionRepository,
            DatasourceRepository datasourceRepository,
            UserRepository userRepository
    ) {

        super(
                configService,
                segmentConfig,
                commonConfig,
                workspaceRepository,
                applicationRepository,
                newPageRepository,
                newActionRepository,
                datasourceRepository,
                userRepository
        );
    }
}
