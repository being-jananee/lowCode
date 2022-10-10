package com.bizBrainz.server.services;

import com.bizBrainz.external.services.EncryptionService;
import com.bizBrainz.server.configurations.EncryptionConfig;
import com.bizBrainz.server.services.ce.EncryptionServiceCEImpl;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl extends EncryptionServiceCEImpl implements EncryptionService {

    public EncryptionServiceImpl(EncryptionConfig encryptionConfig) {
        super(encryptionConfig);
    }
}
