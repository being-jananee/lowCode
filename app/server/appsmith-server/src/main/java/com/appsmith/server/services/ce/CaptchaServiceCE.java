package com.bizBrainz.server.services.ce;

import reactor.core.publisher.Mono;

public interface CaptchaServiceCE {
  
  Mono<Boolean> verify(String recaptchaResponse);
  
}
