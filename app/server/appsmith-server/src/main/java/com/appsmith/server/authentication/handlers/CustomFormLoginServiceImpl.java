package com.bizBrainz.server.authentication.handlers;

import com.bizBrainz.server.authentication.handlers.ce.CustomFormLoginServiceCEImpl;
import com.bizBrainz.server.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomFormLoginServiceImpl extends CustomFormLoginServiceCEImpl {

    @Autowired
    public CustomFormLoginServiceImpl(UserRepository repository) {
        super(repository);
    }
}
