package com.bizBrainz.server.controllers.ce;

import com.bizBrainz.server.dtos.ResponseDTO;
import com.bizBrainz.server.services.UsagePulseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UsagePulseControllerCE {

    private final UsagePulseService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseDTO<Boolean>> create() {
        return service.createPulse()
                .thenReturn(new ResponseDTO<>(HttpStatus.CREATED.value(), true, null));
    }

}
