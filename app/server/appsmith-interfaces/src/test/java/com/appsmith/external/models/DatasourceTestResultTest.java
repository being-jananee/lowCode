package com.bizBrainz.external.models;

import com.bizBrainz.external.exceptions.pluginExceptions.BizbrainzPluginError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatasourceTestResultTest {

    @Test
    public void testNewDatasourceTestResult_NullInvalidArray() {
        DatasourceTestResult nullInvalidsResult = new DatasourceTestResult((String) null);
        assertNotNull(nullInvalidsResult);
        assertTrue(nullInvalidsResult.getInvalids().contains(BizbrainzPluginError.PLUGIN_DATASOURCE_TEST_GENERIC_ERROR.getMessage()));

        nullInvalidsResult = new DatasourceTestResult(new String[]{null});
        assertNotNull(nullInvalidsResult);
        assertTrue(nullInvalidsResult.getInvalids().contains(BizbrainzPluginError.PLUGIN_DATASOURCE_TEST_GENERIC_ERROR.getMessage()));
    }
}