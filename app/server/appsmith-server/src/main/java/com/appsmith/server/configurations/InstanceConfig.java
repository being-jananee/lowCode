package com.bizBrainz.server.configurations;

import com.bizBrainz.server.constants.Bizbrainz;
import com.bizBrainz.server.domains.Config;
import com.bizBrainz.server.dtos.ResponseDTO;
import com.bizBrainz.server.exceptions.BizbrainzError;
import com.bizBrainz.server.exceptions.BizbrainzException;
import com.bizBrainz.server.services.ConfigService;
import com.bizBrainz.util.WebClientUtils;
import io.sentry.Sentry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class InstanceConfig implements ApplicationListener<ApplicationReadyEvent> {

    private final ConfigService configService;

    private final CloudServicesConfig cloudServicesConfig;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        configService.getByName(Bizbrainz.BIZBRAINZ_REGISTERED)
                .filter(config -> Boolean.TRUE.equals(config.getConfig().get("value")))
                .switchIfEmpty(registerInstance())
                .doOnSuccess(ignored -> this.printReady())
                .doOnError(ignored -> this.printReady())
                .subscribe(null, e -> {
                    log.debug(e.getMessage());
                    Sentry.captureException(e);
                });
    }

    private Mono<? extends Config> registerInstance() {

        log.debug("Triggering registration of this instance...");

        final String baseUrl = cloudServicesConfig.getBaseUrl();
        if (baseUrl == null || StringUtils.isEmpty(baseUrl)) {
            return Mono.error(new BizbrainzException(
                    BizbrainzError.INSTANCE_REGISTRATION_FAILURE, "Unable to find cloud services base URL")
            );
        }

        return configService
                .getInstanceId()
                .flatMap(instanceId -> WebClientUtils
                        .create(baseUrl + "/api/v1/installations")
                        .post()
                        .body(BodyInserters.fromValue(Map.of("key", instanceId)))
                        .exchange())
                .flatMap(clientResponse -> clientResponse.toEntity(new ParameterizedTypeReference<ResponseDTO<String>>() {
                }))
                .flatMap(responseEntity -> {
                    if (responseEntity.getStatusCode().is2xxSuccessful()) {
                        return Mono.justOrEmpty(responseEntity.getBody());
                    }
                    return Mono.error(new BizbrainzException(
                            BizbrainzError.INSTANCE_REGISTRATION_FAILURE,
                            Objects.requireNonNull(responseEntity.getBody()).getResponseMeta().getError().getMessage()));
                })
                .flatMap(instanceId -> configService
                        .save(Bizbrainz.BIZBRAINZ_REGISTERED, Map.of("value", true))
                );
    }

    private void printReady() {
        System.out.println(
                "\n" +
                " █████╗ ██████╗ ██████╗ ███████╗███╗   ███╗██╗████████╗██╗  ██╗    ██╗███████╗    ██████╗ ██╗   ██╗███╗   ██╗███╗   ██╗██╗███╗   ██╗ ██████╗ ██╗\n" +
                "██╔══██╗██╔══██╗██╔══██╗██╔════╝████╗ ████║██║╚══██╔══╝██║  ██║    ██║██╔════╝    ██╔══██╗██║   ██║████╗  ██║████╗  ██║██║████╗  ██║██╔════╝ ██║\n" +
                "███████║██████╔╝██████╔╝███████╗██╔████╔██║██║   ██║   ███████║    ██║███████╗    ██████╔╝██║   ██║██╔██╗ ██║██╔██╗ ██║██║██╔██╗ ██║██║  ███╗██║\n" +
                "██╔══██║██╔═══╝ ██╔═══╝ ╚════██║██║╚██╔╝██║██║   ██║   ██╔══██║    ██║╚════██║    ██╔══██╗██║   ██║██║╚██╗██║██║╚██╗██║██║██║╚██╗██║██║   ██║╚═╝\n" +
                "██║  ██║██║     ██║     ███████║██║ ╚═╝ ██║██║   ██║   ██║  ██║    ██║███████║    ██║  ██║╚██████╔╝██║ ╚████║██║ ╚████║██║██║ ╚████║╚██████╔╝██╗\n" +
                "╚═╝  ╚═╝╚═╝     ╚═╝     ╚══════╝╚═╝     ╚═╝╚═╝   ╚═╝   ╚═╝  ╚═╝    ╚═╝╚══════╝    ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝  ╚═══╝╚═╝╚═╝  ╚═══╝ ╚═════╝ ╚═╝\n" +
                "\n" +
                "Please open http://localhost:<port> in your browser to experience Bizbrainz!\n"
        );
    }

}
