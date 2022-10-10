package com.bizBrainz.server.services.ce;

import reactor.core.publisher.Mono;

public interface UsagePulseServiceCE {
    Mono<Void> createPulse();
}
