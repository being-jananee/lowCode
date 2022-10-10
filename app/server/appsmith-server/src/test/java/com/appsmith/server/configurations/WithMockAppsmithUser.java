package com.bizBrainz.server.configurations;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockBizbrainzSecurityContextFactory.class)
public @interface WithMockBizbrainzUser {
    String username() default "anonymousUser";

    String name() default "Anonymous User";
}
