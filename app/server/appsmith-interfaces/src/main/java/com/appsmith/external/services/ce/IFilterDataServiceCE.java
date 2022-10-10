package com.bizBrainz.external.services.ce;

import com.bizBrainz.external.constants.ConditionalOperator;
import com.bizBrainz.external.constants.DataType;
import com.bizBrainz.external.dtos.PreparedStatementValueDTO;
import com.bizBrainz.external.models.Condition;
import com.bizBrainz.external.models.UQIDataFilterParams;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.List;
import java.util.Map;


public interface IFilterDataServiceCE {

    ArrayNode filterDataNew(ArrayNode items, UQIDataFilterParams uqiDataFilterParams);

    ArrayNode filterDataNew(ArrayNode items, UQIDataFilterParams uqiDataFilterParams, Map<DataType, DataType> dataTypeConversionMap);

    void insertAllData(String tableName, ArrayNode items, Map<String, DataType> schema, Map<DataType, DataType> dataTypeConversionMap);

    String generateTable(Map<String, DataType> schema);

    void dropTable(String tableName);

    Map<String, DataType> generateSchema(ArrayNode items, Map<DataType, DataType> dataTypeConversionMap);

    boolean validConditionList(List<Condition> conditionList, Map<String, DataType> schema);

    String generateLogicalExpression(List<Condition> conditions, List<PreparedStatementValueDTO> values,
                                     Map<String, DataType> schema, ConditionalOperator logicOp);

}

