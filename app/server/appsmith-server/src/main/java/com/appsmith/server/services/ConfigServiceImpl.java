package com.bizBrainz.server.services;

import com.bizBrainz.server.repositories.ApplicationRepository;
import com.bizBrainz.server.repositories.ConfigRepository;
import com.bizBrainz.server.repositories.DatasourceRepository;
import com.bizBrainz.server.services.ce.ConfigServiceCEImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConfigServiceImpl extends ConfigServiceCEImpl implements ConfigService {

    public ConfigServiceImpl(ConfigRepository repository,
                             ApplicationRepository applicationRepository,
                             DatasourceRepository datasourceRepository) {

        super(repository, applicationRepository, datasourceRepository);
    }
}
