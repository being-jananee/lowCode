package com.external.config;

import com.bizBrainz.external.exceptions.pluginExceptions.BizbrainzPluginError;
import com.bizBrainz.external.exceptions.pluginExceptions.BizbrainzPluginException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * API reference: https://developers.google.com/sheets/api/reference/rest/v4/spreadsheets.values/clear
 */
public class ClearMethod implements ExecutionMethod {

    ObjectMapper objectMapper;

    public ClearMethod(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean validateExecutionMethodRequest(MethodConfig methodConfig) {
        if (methodConfig.getSpreadsheetId() == null || methodConfig.getSpreadsheetId().isBlank()) {
            throw new BizbrainzPluginException(BizbrainzPluginError.PLUGIN_EXECUTE_ARGUMENT_ERROR, "Missing required field Spreadsheet Url");
        }
        if (methodConfig.getSpreadsheetRange() == null || methodConfig.getSpreadsheetRange().isBlank()) {
            throw new BizbrainzPluginException(BizbrainzPluginError.PLUGIN_EXECUTE_ARGUMENT_ERROR, "Missing required field 'Cell Range'");
        }
        return true;

    }

    @Override
    public WebClient.RequestHeadersSpec<?> getExecutionClient(WebClient webClient, MethodConfig methodConfig) {


        UriComponentsBuilder uriBuilder = getBaseUriBuilder(this.BASE_SHEETS_API_URL,
                methodConfig.getSpreadsheetId() /* spreadsheet Id */
                        + "/values/"
                        + URLEncoder.encode(methodConfig.getSpreadsheetRange(), StandardCharsets.UTF_8) /* spreadsheet Range */
                        + ":clear",
                true
        );

        return webClient.method(HttpMethod.POST)
                .uri(uriBuilder.build(true).toUri())
                .body(BodyInserters.empty());
    }

}
