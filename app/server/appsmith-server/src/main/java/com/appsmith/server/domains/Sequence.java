package com.bizBrainz.server.domains;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sequence {

    private String name;

    private Long nextNumber;

}
