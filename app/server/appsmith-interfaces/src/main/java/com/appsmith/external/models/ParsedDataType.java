package com.bizBrainz.external.models;

import com.bizBrainz.external.constants.DisplayDataType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ParsedDataType {
    private final DisplayDataType dataType;
}
