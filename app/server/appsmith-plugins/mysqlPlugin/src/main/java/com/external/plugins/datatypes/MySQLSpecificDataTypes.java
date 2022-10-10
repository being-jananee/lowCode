package com.external.plugins.datatypes;

import com.bizBrainz.external.datatypes.BizbrainzType;
import com.bizBrainz.external.datatypes.BigDecimalType;
import com.bizBrainz.external.datatypes.ClientDataType;
import com.bizBrainz.external.datatypes.DoubleType;
import com.bizBrainz.external.datatypes.IntegerType;
import com.bizBrainz.external.datatypes.JsonObjectType;
import com.bizBrainz.external.datatypes.LongType;
import com.bizBrainz.external.datatypes.NullType;
import com.bizBrainz.external.datatypes.StringType;
import com.bizBrainz.external.datatypes.TimeType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLSpecificDataTypes {
    public static final Map<ClientDataType, List<BizbrainzType>> pluginSpecificTypes;

    static {
        pluginSpecificTypes = new HashMap<>();
        pluginSpecificTypes.put(ClientDataType.NULL, List.of(new NullType()));

        pluginSpecificTypes.put(ClientDataType.BOOLEAN, List.of(new MySQLBooleanType()));

        pluginSpecificTypes.put(ClientDataType.NUMBER, List.of(
                new IntegerType(),
                new LongType(),
                new DoubleType(),
                new BigDecimalType()
        ));

        pluginSpecificTypes.put(ClientDataType.OBJECT, List.of(new JsonObjectType()));

        pluginSpecificTypes.put(ClientDataType.STRING, List.of(
                new TimeType(),
                new MySQLDateType(),
                new MySQLDateTimeType(),
                new StringType()
        ));
    }

}
