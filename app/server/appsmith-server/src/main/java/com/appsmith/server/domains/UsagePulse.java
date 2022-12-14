package com.bizBrainz.server.domains;

import com.bizBrainz.external.models.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document
public class UsagePulse extends BaseDomain {

    private String email;

}
