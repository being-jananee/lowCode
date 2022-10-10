package com.bizBrainz.server.controllers.ce;

import com.bizBrainz.external.models.Provider;
import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.dtos.ResponseDTO;
import com.bizBrainz.server.services.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.util.List;


@RequestMapping(Url.PROVIDER_URL)
@Slf4j
public class ProviderControllerCE extends BaseController<ProviderService, Provider, String> {
    public ProviderControllerCE(ProviderService service) {
        super(service);
    }

    @GetMapping("/categories")
    public Mono<ResponseDTO<List<String>>> getAllCategories() {
        return service.getAllCategories()
                .map(resources -> new ResponseDTO<>(HttpStatus.OK.value(), resources, null));
    }
}
