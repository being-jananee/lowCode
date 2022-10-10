package com.external.utils;

import com.bizBrainz.external.exceptions.pluginExceptions.BizbrainzPluginError;
import com.bizBrainz.external.exceptions.pluginExceptions.BizbrainzPluginException;
import com.bizBrainz.external.models.ActionConfiguration;
import com.bizBrainz.external.models.Property;
import graphql.parser.InvalidSyntaxException;
import graphql.parser.Parser;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static com.bizBrainz.external.helpers.PluginUtils.getValueSafelyFromPropertyList;
import static com.bizBrainz.external.helpers.PluginUtils.parseStringIntoJSONObject;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public class GraphQLBodyUtils {
    public static final int QUERY_VARIABLES_INDEX = 1;
    public static final int PAGINATION_DATA_INDEX = 2;
    public static final String QUERY_KEY = "query";
    public static final String VARIABLES_KEY = "variables";

    public static String convertToGraphQLPOSTBodyFormat(ActionConfiguration actionConfiguration) throws BizbrainzPluginException {
        JSONObject query = new JSONObject();
        query.put(QUERY_KEY, actionConfiguration.getBody());

        final List<Property> properties = actionConfiguration.getPluginSpecifiedTemplates();
        String variables = getValueSafelyFromPropertyList(properties, QUERY_VARIABLES_INDEX, String.class);
        if (!isBlank(variables)) {
            try {
                JSONObject json = parseStringIntoJSONObject(variables);
                query.put(VARIABLES_KEY, json);
            } catch (JSONException | ClassCastException e) {
                throw new BizbrainzPluginException(BizbrainzPluginError.PLUGIN_EXECUTE_ARGUMENT_ERROR, "GraphQL query " +
                        "variables are not in proper JSON format: " + e.getMessage());
            }
        }

        return query.toString();
    }

    public static void validateBodyAndVariablesSyntax(ActionConfiguration actionConfiguration) throws BizbrainzPluginException {
        if (isBlank(actionConfiguration.getBody())) {
            throw new BizbrainzPluginException(
                    BizbrainzPluginError.PLUGIN_EXECUTE_ARGUMENT_ERROR,
                    "Your GraphQL query body is empty. Please edit the 'Body' tab of your GraphQL API to provide a " +
                            "query body.");
        }

        Parser graphqlParser = new Parser();
        try {
            graphqlParser.parseDocument(actionConfiguration.getBody());
        } catch (InvalidSyntaxException e) {
            throw new BizbrainzPluginException(
                    BizbrainzPluginError.PLUGIN_EXECUTE_ARGUMENT_ERROR,
                    "Invalid GraphQL body: " + e.getMessage());
        }
        final List<Property> properties = actionConfiguration.getPluginSpecifiedTemplates();
        String variables = getValueSafelyFromPropertyList(properties, QUERY_VARIABLES_INDEX, String.class);
        if (!isEmpty(variables)) {
            try {
                parseStringIntoJSONObject(variables);
            } catch (JSONException e) {
                throw new BizbrainzPluginException(
                        BizbrainzPluginError.PLUGIN_EXECUTE_ARGUMENT_ERROR,
                        "GraphQL query variables are not in proper JSON format: " + e.getMessage()
                );
            }
        }
    }

    public static List<Property> getGraphQLQueryParamsForBodyAndVariables(ActionConfiguration actionConfiguration) {
        List<Property> queryParams = new ArrayList<>();
        queryParams.add(new Property(QUERY_KEY, actionConfiguration.getBody()));

        final List<Property> properties = actionConfiguration.getPluginSpecifiedTemplates();
        String variables = getValueSafelyFromPropertyList(properties, QUERY_VARIABLES_INDEX, String.class);
        if (!isBlank(variables)) {
            queryParams.add(new Property(VARIABLES_KEY, variables));
        }

        return queryParams;
    }
}
