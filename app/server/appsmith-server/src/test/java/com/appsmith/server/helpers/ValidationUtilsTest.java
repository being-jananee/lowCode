package com.bizBrainz.server.helpers;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidationUtilsTest {

    @Test
    public void validateEmailCsv() {
        assertThat(ValidationUtils.validateEmailCsv("")).isFalse();
        assertThat(ValidationUtils.validateEmailCsv(null)).isFalse();
        assertThat(ValidationUtils.validateEmailCsv(" ")).isFalse();
        assertThat(ValidationUtils.validateEmailCsv("a@bizBrainz.com")).isTrue();
        assertThat(ValidationUtils.validateEmailCsv("a@bizBrainz.com,a@bizBrainz.com")).isTrue();
        assertThat(ValidationUtils.validateEmailCsv("a@bizBrainz.com, b@bizBrainz.com")).isTrue();
        assertThat(ValidationUtils.validateEmailCsv("a@bizBrainz.com , a@bizBrainz.com")).isTrue();
        assertThat(ValidationUtils.validateEmailCsv("a@bizBrainz.com  ,  a@bizBrainz.com")).isTrue();
        assertThat(ValidationUtils.validateEmailCsv("a@bizBrainz.com  ,  b@bizBrainz.com ,c@bizBrainz.com")).isTrue();
        assertThat(ValidationUtils.validateEmailCsv(" a@bizBrainz.com , b@bizBrainz.com ")).isTrue();

        assertThat(ValidationUtils.validateEmailCsv("a@bizBrainz.com,a@bizBrainz.com,xyz")).isFalse();
        assertThat(ValidationUtils.validateEmailCsv("a@bizBrainz.com,b@bizBrainz.com,,")).isFalse();
        assertThat(ValidationUtils.validateEmailCsv("a@bizBrainz.com,b@bizBrainz.com, ")).isFalse();
        assertThat(ValidationUtils.validateEmailCsv(",,")).isFalse();
        assertThat(ValidationUtils.validateEmailCsv(",")).isFalse();
        assertThat(ValidationUtils.validateEmailCsv("a@bizBrainz.com,,")).isFalse();
    }
}