package com.bizBrainz.server.configurations;

import com.bizBrainz.server.domains.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockBizbrainzSecurityContextFactory implements WithSecurityContextFactory<WithMockBizbrainzUser> {

    @Override
    public SecurityContext createSecurityContext(WithMockBizbrainzUser mockBizbrainzUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        User principal = new User();
        principal.setId(mockBizbrainzUser.username());
        principal.setEmail(mockBizbrainzUser.username());
        principal.setName(mockBizbrainzUser.name());
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}
