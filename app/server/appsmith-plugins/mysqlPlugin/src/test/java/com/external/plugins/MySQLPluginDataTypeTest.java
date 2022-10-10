package com.external.plugins;

import com.bizBrainz.external.datatypes.BizbrainzType;
import com.bizBrainz.external.datatypes.BigDecimalType;
import com.bizBrainz.external.datatypes.ClientDataType;
import com.bizBrainz.external.datatypes.DoubleType;
import com.bizBrainz.external.datatypes.FallbackType;
import com.bizBrainz.external.datatypes.IntegerType;
import com.bizBrainz.external.datatypes.JsonObjectType;
import com.bizBrainz.external.datatypes.LongType;
import com.bizBrainz.external.datatypes.NullType;
import com.bizBrainz.external.datatypes.StringType;
import com.bizBrainz.external.datatypes.TimeType;
import com.bizBrainz.external.helpers.DataTypeServiceUtils;
import com.external.plugins.datatypes.MySQLBooleanType;
import com.external.plugins.datatypes.MySQLDateTimeType;
import com.external.plugins.datatypes.MySQLDateType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MySQLPluginDataTypeTest {
    private static final Map<ClientDataType, List<BizbrainzType>> pluginSpecificTypes;

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

        /*
            JsonObjectType is the preferred server-side data type when the client-side data type is of type OBJECT.
            Fallback server-side data type for client-side OBJECT type is String.
         */
        pluginSpecificTypes.put(ClientDataType.OBJECT, List.of(new JsonObjectType()));

        pluginSpecificTypes.put(ClientDataType.STRING, List.of(
                new TimeType(),
                new MySQLDateType(),
                new MySQLDateTimeType(),
                new StringType()
        ));
    }

    @Test
    public void shouldBeNullType() {
        String value = "null";

        BizbrainzType bizBrainzType = DataTypeServiceUtils.getBizbrainzType(ClientDataType.NULL, value, pluginSpecificTypes);
        assertTrue(bizBrainzType instanceof NullType);
        assertEquals(bizBrainzType.performSmartSubstitution(value), null);
    }

    @Test
    public void shouldBeMySQLBooleanType() {
        String[] values = {
                "true",
                "false"
        };
        Map<String, String> booleanValueMap = Map.of(
                "true", "1",
                "false", "0"
        );

        for (String value : values) {
            BizbrainzType bizBrainzType = DataTypeServiceUtils.getBizbrainzType(ClientDataType.BOOLEAN, value, pluginSpecificTypes);
            assertTrue(bizBrainzType instanceof MySQLBooleanType);
            assertEquals(bizBrainzType.performSmartSubstitution(value), booleanValueMap.get(value));
        }
    }

    @Test
    public void shouldBeIntegerType() {
        String[] values = {
                "0",
                "7166",
                String.valueOf(Integer.MIN_VALUE),
                String.valueOf(Integer.MAX_VALUE)
        };

        for (String value : values) {
            BizbrainzType bizBrainzType = DataTypeServiceUtils.getBizbrainzType(ClientDataType.NUMBER, value, pluginSpecificTypes);
            assertTrue(bizBrainzType instanceof IntegerType);
            assertEquals(bizBrainzType.performSmartSubstitution(value), value);
        }
    }

    @Test
    public void shouldBeLongType() {
        String[] values = {
                "2147483648",
                "-2147483649"
        };

        for (String value : values) {
            BizbrainzType bizBrainzType = DataTypeServiceUtils.getBizbrainzType(ClientDataType.NUMBER, value, pluginSpecificTypes);
            assertTrue(bizBrainzType instanceof LongType);
            assertEquals(bizBrainzType.performSmartSubstitution(value), value);
        }
    }

    @Test
    public void shouldBeDoubleType() {
        String[] values = {
                "323.23",
                String.valueOf(Double.MIN_VALUE),
                String.valueOf(Double.MAX_VALUE),
                String.valueOf(Double.POSITIVE_INFINITY),
                String.valueOf(Double.NEGATIVE_INFINITY)
        };

        for (String value : values) {
            BizbrainzType bizBrainzType = DataTypeServiceUtils.getBizbrainzType(ClientDataType.NUMBER, value, pluginSpecificTypes);
            assertTrue(bizBrainzType instanceof DoubleType);
            assertEquals(bizBrainzType.performSmartSubstitution(value), value);
        }
    }

    @Test
    public void shouldBeJsonObjectTypeOrStringType() {
        String[] values = {
                "{\"a\":97,\"A\":65}",
                "{\"a\":97,\"A\":65"
        };
        for (String value : values) {
            BizbrainzType bizBrainzType = DataTypeServiceUtils.getBizbrainzType(ClientDataType.OBJECT, value, pluginSpecificTypes);
            assertTrue(bizBrainzType instanceof JsonObjectType || bizBrainzType instanceof FallbackType);
        }

    }

    @Test
    public void shouldBeTimeType() {
        String[] values = {
                "10:15:30",
                "10:15"
        };
        for (String value : values) {
            BizbrainzType bizBrainzType = DataTypeServiceUtils.getBizbrainzType(ClientDataType.STRING, value, pluginSpecificTypes);
            assertTrue(bizBrainzType instanceof TimeType);
        }
    }

    @Test
    public void shouldBeDateType() {
        String[] values = {
                "2011-12-03",
                "20111203",
                "2022/09/25",
                "220924"
        };
        for (String value : values) {
            BizbrainzType bizBrainzType = DataTypeServiceUtils.getBizbrainzType(ClientDataType.STRING, value, pluginSpecificTypes);
            assertTrue(bizBrainzType instanceof MySQLDateType);
        }
    }

    @Test
    public void shouldBeMySQLDateTimeType() {
        String[] values = {
                "2021-03-24 14:05:34",
                "2011-12-03T10:15:30+01:00",
                "2022/09/05 23:55:33",
                "20220905234556",
                "220905234556"
        };
        for (String value : values) {
            BizbrainzType bizBrainzType = DataTypeServiceUtils.getBizbrainzType(ClientDataType.STRING, value, pluginSpecificTypes);
            assertTrue(bizBrainzType instanceof MySQLDateTimeType);
        }
    }

    @Test
    public void shouldBeStringType() {
        String[] values = {
                "Hello, world!",
                "123",
                "098876",
                "2022/09/252",
                "",
                "10:15:30+06:00",
                "2021-03-24 14:05:343"
        };
        for (String value : values) {
            BizbrainzType bizBrainzType = DataTypeServiceUtils.getBizbrainzType(ClientDataType.STRING, value, pluginSpecificTypes);
            assertTrue(bizBrainzType instanceof StringType);
        }
    }
}
