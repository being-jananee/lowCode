package com.bizBrainz.external.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApiTemplateConfiguration implements BizbrainzDomain {
    String documentation; //Documentation for this particular API comes here
    String documentationUrl; //URL for this particular api's documentation comes here
    ActionExecutionResult sampleResponse;
}
