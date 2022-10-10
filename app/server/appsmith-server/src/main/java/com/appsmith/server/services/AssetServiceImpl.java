package com.bizBrainz.server.services;

import com.bizBrainz.server.repositories.AssetRepository;
import com.bizBrainz.server.services.ce.AssetServiceCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AssetServiceImpl extends AssetServiceCEImpl implements AssetService {

    public AssetServiceImpl(AssetRepository repository,
                            AnalyticsService analyticsService) {

        super(repository, analyticsService);
    }
}
