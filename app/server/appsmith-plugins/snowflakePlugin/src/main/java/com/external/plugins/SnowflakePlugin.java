package com.external.plugins;

import com.bizBrainz.external.exceptions.pluginExceptions.BizbrainzPluginError;
import com.bizBrainz.external.exceptions.pluginExceptions.BizbrainzPluginException;
import com.bizBrainz.external.exceptions.pluginExceptions.StaleConnectionException;
import com.bizBrainz.external.models.ActionConfiguration;
import com.bizBrainz.external.models.ActionExecutionRequest;
import com.bizBrainz.external.models.ActionExecutionResult;
import com.bizBrainz.external.models.DBAuth;
import com.bizBrainz.external.models.DatasourceConfiguration;
import com.bizBrainz.external.models.DatasourceStructure;
import com.bizBrainz.external.models.DatasourceTestResult;
import com.bizBrainz.external.plugins.BasePlugin;
import com.bizBrainz.external.plugins.PluginExecutor;
import com.external.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.pf4j.Extension;
import org.pf4j.PluginWrapper;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import static com.external.utils.ExecutionUtils.getRowsFromQueryResult;
import static com.external.utils.ValidationUtils.validateWarehouseDatabaseSchema;

@Slf4j
public class SnowflakePlugin extends BasePlugin {

    public SnowflakePlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension
    public static class SnowflakePluginExecutor implements PluginExecutor<Connection> {

        private final Scheduler scheduler = Schedulers.elastic();

        @Override
        public Mono<ActionExecutionResult> execute(Connection connection, DatasourceConfiguration datasourceConfiguration, ActionConfiguration actionConfiguration) {

            String query = actionConfiguration.getBody();

            if (query == null) {
                return Mono.error(new BizbrainzPluginException(
                        BizbrainzPluginError.PLUGIN_EXECUTE_ARGUMENT_ERROR,
                        "Missing required parameter: Query."));
            }

            return Mono
                    .fromCallable(() -> {
                        try {
                            // Connection staleness is checked as part of this method call.
                            return getRowsFromQueryResult(connection, query);
                        } catch (BizbrainzPluginException | StaleConnectionException e) {
                            throw e;
                        }
                    })
                    .map(rowsList -> {
                        ActionExecutionResult result = new ActionExecutionResult();
                        result.setBody(objectMapper.valueToTree(rowsList));
                        result.setIsExecutionSuccess(true);
                        ActionExecutionRequest request = new ActionExecutionRequest();
                        request.setQuery(query);
                        result.setRequest(request);
                        return result;
                    })
                    .subscribeOn(scheduler);
        }

        @Override
        public Mono<Connection> datasourceCreate(DatasourceConfiguration datasourceConfiguration) {
            try {
                Class.forName("net.snowflake.client.jdbc.SnowflakeDriver");
            } catch (ClassNotFoundException ex) {
                log.debug("Driver not found");
                return Mono.error(new BizbrainzPluginException(BizbrainzPluginError.PLUGIN_ERROR, ex.getMessage()));
            }
            DBAuth authentication = (DBAuth) datasourceConfiguration.getAuthentication();
            Properties properties = new Properties();
            properties.setProperty("user", authentication.getUsername());
            properties.setProperty("password", authentication.getPassword());
            properties.setProperty("warehouse", String.valueOf(datasourceConfiguration.getProperties().get(0).getValue()));
            properties.setProperty("db", String.valueOf(datasourceConfiguration.getProperties().get(1).getValue()));
            properties.setProperty("schema", String.valueOf(datasourceConfiguration.getProperties().get(2).getValue()));
            properties.setProperty("role", String.valueOf(datasourceConfiguration.getProperties().get(3).getValue()));

            return Mono
                    .fromCallable(() -> {
                        Connection conn;
                        try {
                            conn = DriverManager.getConnection("jdbc:snowflake://" + datasourceConfiguration.getUrl() + ".snowflakecomputing.com", properties);
                        } catch (SQLException e) {
                            log.error("Exception caught when connecting to Snowflake endpoint: " + datasourceConfiguration.getUrl() + ". Cause: ", e);
                            throw new BizbrainzPluginException(BizbrainzPluginError.PLUGIN_DATASOURCE_ARGUMENT_ERROR, e.getMessage());
                        }
                        if (conn == null) {
                            throw new BizbrainzPluginException(BizbrainzPluginError.PLUGIN_ERROR, "Unable to create connection to Snowflake URL");
                        }
                        return conn;
                    })
                    .subscribeOn(scheduler);
        }

        @Override
        public void datasourceDestroy(Connection connection) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwable) {
                    log.error("Exception caught when closing Snowflake connection. Cause: ", throwable);
                }
            }
        }

        @Override
        public Set<String> validateDatasource(DatasourceConfiguration datasourceConfiguration) {
            Set<String> invalids = new HashSet<>();

            if (StringUtils.isEmpty(datasourceConfiguration.getUrl())) {
                invalids.add("Missing Snowflake URL.");
            }

            if (datasourceConfiguration.getProperties() != null
                    && (datasourceConfiguration.getProperties().size() < 1
                    || datasourceConfiguration.getProperties().get(0) == null
                    || datasourceConfiguration.getProperties().get(0).getValue() == null
                    || StringUtils.isEmpty(String.valueOf(datasourceConfiguration.getProperties().get(0).getValue())))) {
                invalids.add("Missing warehouse name.");
            }

            if (datasourceConfiguration.getProperties() != null
                    && (datasourceConfiguration.getProperties().size() < 2
                    || datasourceConfiguration.getProperties().get(1) == null
                    || datasourceConfiguration.getProperties().get(1).getValue() == null
                    || StringUtils.isEmpty(String.valueOf(datasourceConfiguration.getProperties().get(1).getValue())))) {
                invalids.add("Missing database name.");
            }

            if (datasourceConfiguration.getProperties() != null
                    && (datasourceConfiguration.getProperties().size() < 3
                    || datasourceConfiguration.getProperties().get(2) == null
                    || datasourceConfiguration.getProperties().get(2).getValue() == null
                    || StringUtils.isEmpty(String.valueOf(datasourceConfiguration.getProperties().get(2).getValue())))) {
                invalids.add("Missing schema name.");
            }

            if (datasourceConfiguration.getAuthentication() == null) {
                invalids.add("Missing authentication details.");
            } else {
                DBAuth authentication = (DBAuth) datasourceConfiguration.getAuthentication();
                if (StringUtils.isEmpty(authentication.getUsername())) {
                    invalids.add("Missing username for authentication.");
                }

                if (StringUtils.isEmpty(authentication.getPassword())) {
                    invalids.add("Missing password for authentication.");
                }
            }

            return invalids;
        }

        @Override
        public Mono<DatasourceTestResult> testDatasource(DatasourceConfiguration datasourceConfiguration) {
            return datasourceCreate(datasourceConfiguration)
                    .flatMap(connection -> {
                        if (connection != null) {
                            try {
                                Set<String> invalids = validateWarehouseDatabaseSchema(connection);
                                if (!invalids.isEmpty()) {
                                    return Mono.error(
                                            new BizbrainzPluginException(
                                                    BizbrainzPluginError.PLUGIN_DATASOURCE_ARGUMENT_ERROR,
                                                    invalids.toArray()[0]
                                            )
                                    );
                                }
                                connection.close();
                            } catch (SQLException throwable) {
                                log.error("Exception caught while testing Snowflake datasource. Cause: ", throwable);
                                return Mono.error(throwable);
                            }
                        }

                        return Mono.just(new DatasourceTestResult());
                    })
                    .onErrorResume(error -> Mono.just(new DatasourceTestResult(error.getMessage())));
        }

        @Override
        public Mono<DatasourceStructure> getStructure(Connection connection, DatasourceConfiguration datasourceConfiguration) {
            final DatasourceStructure structure = new DatasourceStructure();
            final Map<String, DatasourceStructure.Table> tablesByName = new LinkedHashMap<>();
            final Map<String, DatasourceStructure.Key> keyRegistry = new HashMap<>();

            return Mono
                    .fromSupplier(() -> {
                        try {
                            // Connection staleness is checked as part of this method call.
                            Set<String> invalids = validateWarehouseDatabaseSchema(connection);
                            if (!invalids.isEmpty()) {
                                throw new BizbrainzPluginException(
                                        BizbrainzPluginError.PLUGIN_DATASOURCE_ARGUMENT_ERROR,
                                        invalids.toArray()[0]
                                );
                            }
                            Statement statement = connection.createStatement();
                            final String columnsQuery = SqlUtils.COLUMNS_QUERY + "'"
                                    + datasourceConfiguration.getProperties().get(2).getValue() + "'";
                            ResultSet resultSet = statement.executeQuery(columnsQuery);

                            while (resultSet.next()) {
                                SqlUtils.getTableInfo(resultSet, tablesByName);
                            }

                            resultSet = statement.executeQuery(SqlUtils.PRIMARY_KEYS_QUERY);
                            while (resultSet.next()) {
                                SqlUtils.getPrimaryKeyInfo(resultSet, tablesByName, keyRegistry);
                            }

                            resultSet = statement.executeQuery(SqlUtils.FOREIGN_KEYS_QUERY);
                            while (resultSet.next()) {
                                SqlUtils.getForeignKeyInfo(resultSet, tablesByName, keyRegistry);
                            }

                            /* Get templates for each table and put those in. */
                            SqlUtils.getTemplates(tablesByName);
                            structure.setTables(new ArrayList<>(tablesByName.values()));
                            for (DatasourceStructure.Table table : structure.getTables()) {
                                table.getKeys().sort(Comparator.naturalOrder());
                            }
                        } catch (SQLException throwable) {
                            log.error("Exception caught while fetching structure of Snowflake datasource. Cause: ", throwable);
                            throw new BizbrainzPluginException(BizbrainzPluginError.PLUGIN_ERROR, throwable.getMessage());
                        }
                        return structure;
                    })
                    .subscribeOn(scheduler);
        }
    }
}
