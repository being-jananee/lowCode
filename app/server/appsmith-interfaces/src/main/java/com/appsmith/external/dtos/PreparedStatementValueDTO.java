package com.bizBrainz.external.dtos;

import com.bizBrainz.external.constants.DataType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PreparedStatementValueDTO {

    String value;

    DataType dataType;
}
