package com.bizBrainz.external.datatypes;

import com.bizBrainz.external.constants.DataType;
import com.bizBrainz.external.exceptions.pluginExceptions.BizbrainzPluginError;
import com.bizBrainz.external.exceptions.pluginExceptions.BizbrainzPluginException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.Exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;

public class TimestampType implements BizbrainzType {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean test(String s) {
        try {
            final DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    .toFormatter();
            LocalDateTime.parse(s, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException ex) {
            // Not timestamp
        }
        return false;
    }

    @Override
    public String performSmartSubstitution(String s) {
        try {
            String valueAsString = objectMapper.writeValueAsString(s);
            return Matcher.quoteReplacement(valueAsString);
        } catch (JsonProcessingException e) {
            throw Exceptions.propagate(
                    new BizbrainzPluginException(
                            BizbrainzPluginError.PLUGIN_EXECUTE_ARGUMENT_ERROR,
                            s,
                            e.getMessage()
                    )
            );
        }
    }

    @Override
    public DataType type() {
        return DataType.TIMESTAMP;
    }
}