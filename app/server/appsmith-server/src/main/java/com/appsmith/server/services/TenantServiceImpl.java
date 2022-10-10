package com.bizBrainz.server.services;

import com.bizBrainz.server.repositories.TenantRepository;
import com.bizBrainz.server.services.ce.TenantServiceCEImpl;
import org.springframework.stereotype.Service;

@Service
public class TenantServiceImpl extends TenantServiceCEImpl implements TenantService{
    
    public TenantServiceImpl(TenantRepository tenantRepository) {
        super(tenantRepository);
    }
}
