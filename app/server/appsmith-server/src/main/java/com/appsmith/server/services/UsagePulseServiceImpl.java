package com.bizBrainz.server.services;

import com.bizBrainz.server.repositories.UsagePulseRepository;
import com.bizBrainz.server.services.ce.UsagePulseServiceCEImpl;
import org.springframework.stereotype.Service;

@Service
public class UsagePulseServiceImpl extends UsagePulseServiceCEImpl implements UsagePulseService {

    public UsagePulseServiceImpl(UsagePulseRepository repository, SessionUserService sessionUserService) {
        super(repository, sessionUserService);
    }

}
