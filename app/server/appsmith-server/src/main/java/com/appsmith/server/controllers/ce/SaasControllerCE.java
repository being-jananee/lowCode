package com.bizBrainz.server.controllers.ce;

import com.bizBrainz.external.models.Datasource;
import com.bizBrainz.server.constants.FieldName;
import com.bizBrainz.server.constants.Url;
import com.bizBrainz.server.dtos.ResponseDTO;
import com.bizBrainz.server.solutions.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping(Url.SAAS_URL)
public class SaasControllerCE {

    private final AuthenticationService authenticationService;

    @Autowired
    public SaasControllerCE(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/{datasourceId}/pages/{pageId}/oauth")
    public Mono<ResponseDTO<String>> getBizbrainzToken(@PathVariable String datasourceId,
                                                      @PathVariable String pageId,
                                                      @RequestHeader(name = FieldName.BRANCH_NAME, required = false) String branchName,
                                                      @RequestParam(required = false) String importForGit,
                                                      ServerWebExchange serverWebExchange) {

        log.debug("Going to retrieve token request URL for datasource with id: {} and page id: {}", datasourceId, pageId);
        return authenticationService.getBizbrainzToken(datasourceId, pageId, branchName, serverWebExchange.getRequest(), importForGit)
                .map(token -> new ResponseDTO<>(HttpStatus.OK.value(), token, null));
    }

    @PostMapping("/{datasourceId}/token")
    public Mono<ResponseDTO<Datasource>> getAccessToken(@PathVariable String datasourceId, @RequestParam String bizBrainzToken, ServerWebExchange serverWebExchange) {

        log.debug("Received callback for an OAuth2 authorization request");
        return authenticationService.getAccessTokenFromCloud(datasourceId, bizBrainzToken)
                .map(datasource -> new ResponseDTO<>(HttpStatus.OK.value(), datasource, null));
    }

}
