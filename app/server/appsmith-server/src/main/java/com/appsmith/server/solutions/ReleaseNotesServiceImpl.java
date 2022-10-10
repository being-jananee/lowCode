package com.bizBrainz.server.solutions;

import com.bizBrainz.server.configurations.CloudServicesConfig;
import com.bizBrainz.server.configurations.CommonConfig;
import com.bizBrainz.server.configurations.ProjectProperties;
import com.bizBrainz.server.configurations.SegmentConfig;
import com.bizBrainz.server.services.ConfigService;
import com.bizBrainz.server.solutions.ce.ReleaseNotesServiceCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ReleaseNotesServiceImpl extends ReleaseNotesServiceCEImpl implements ReleaseNotesService {

    public ReleaseNotesServiceImpl(CloudServicesConfig cloudServicesConfig,
                                   SegmentConfig segmentConfig,
                                   ConfigService configService,
                                   ProjectProperties projectProperties,
                                   CommonConfig commonConfig) {

        super(cloudServicesConfig, segmentConfig, configService, projectProperties, commonConfig);
    }
}
