/*
package utils;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.RiakFuture;
import com.basho.riak.client.core.RiakNode;
import com.basho.riak.client.core.operations.PingOperation;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import exceptions.*;
import interfaces.Connectable;
import interfaces.Searchable;

import org.apache.commons.io.FileUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.neo4j.driver.internal.logging.JULogging;
import org.neo4j.driver.v1.*;
import play.Logger;
import play.Play;
import play.vfs.VirtualFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.beans.PropertyVetoException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static constants.APIConstants.APPLICATION_NAME;

*/
/**
 * APIUtils
 * <p>
 * A Singleton utility class for the {@link controllers.API API} class. All persistence configurations and 3rd party integrations
 * are stored here.
 * </p>
 *
 * @author Maica Ballangan
 * @since v1
 *//*

public class Persistence {
    // DB
    private static final String REPORTING_HOST = Play.configuration.getProperty("reporting.host");
    private static final int REPORTING_PORT = Integer.parseInt(Play.configuration.getProperty("reporting.port"));
    private static final String REPORTING_USER = Play.configuration.getProperty("reporting.user");
    private static final String REPORTING_PASSWORD = Play.configuration.getProperty("reporting.password");
    public static final String REPORTING_EXTENSION = Play.configuration.getProperty("reporting.extension");
    public static final String REPORTING_DRIVER = Play.configuration.getProperty("reporting.driver");
    public static final int REPORTING_TIMEOUT = Integer.parseInt(Play.configuration.getProperty("reporting.timeout"));

    // Persistence connections
    public static final Persistence.ReportingPool REPORTING = new ReportingPool.ReportingBuilder(REPORTING_HOST, REPORTING_PORT).autocommit(true).user(REPORTING_USER).password(REPORTING_PASSWORD).build();

    */
/**
     * APIUtils private constructor - This is a singleton class
     *//*

    private Persistence() {
        // private utility class constructor
        throw new IllegalStateException("Utility class");
    }

    */
/**
     * Abstract Connection Pool which all persistence connections will extend
     *
     * @param <T> the type of connection pool
     *//*

    private abstract static class AbstractPool<T> {

        private final Persistence type;
        private final String host;
        private final int port;

        private AbstractPool(final Builder<?> builder) {
            this.type = builder.type;
            this.host = builder.host;
            this.port = builder.port;
        }

        public Persistence getType() {
            return type;
        }

        public String getHost() {
            return host;
        }

        public int getPort() {
            return port;
        }

        // Builder
        private abstract static class Builder<T extends AbstractPool> {
            private final String host;
            private final int port;

            private Builder(final String host, final int port) {
                this.host = Objects.requireNonNull(host);
                this.port = Objects.requireNonNull(port);
            }

            protected abstract T build();
        }
    }

    */
/**
     * ReportingPool
     * <p>The persistence pool for reporting.</p>
     *//*

    public static class ReportingPool extends AbstractPool<Connection> {

        private ComboPooledDataSource pool;
        private boolean autocommit;
        private String user;
        private String password;
        private String database;

        private ReportingPool(ReportingBuilder reportingBuilder) {
            super(reportingBuilder);
            this.autocommit = reportingBuilder.autocommit;
            this.user = reportingBuilder.user;
            this.password = reportingBuilder.password;
            this.database = reportingBuilder.database;
            if (!startup()) {
                pool = null;
            }
        }

        */
/**
         * If connection exists
         *//*

        @Override
        public boolean exists() {
            return pool != null;
        }

        */
/**
         * Get connection
         *
         * @return connection
         *//*

        @Override
        public Connection getConnection() throws ReportingException {
            if (pool == null) {
                throw new ReportingException(getNotConnectedMessage());
            }

            try {
                return pool.getConnection();
            } catch (SQLException e) {
                throw new ReportingException(e);
            }
        }

        */
/**
         * Close the connection
         *
         * @return true if connection successfully closed, false otherwise
         *//*

        @Override
        public boolean close() {
            if (pool != null) {
                pool.close();
            }
            return true;
        }

        @Override
        public void init() throws PersistenceException {
            try {
                // Initialize pool and configure connection parameters
                pool = new ComboPooledDataSource();
                pool.setDriverClass(REPORTING_DRIVER);
                pool.setJdbcUrl("jdbc:postgresql://" + getHost() + ":" + getPort() + "/" + database);
                pool.setUser(user);
                pool.setPassword(password);

                // Set optional parameters
                pool.setInitialPoolSize(10);
                pool.setMinPoolSize(10);
                pool.setMaxPoolSize(30);
                pool.setAutoCommitOnClose(autocommit);
                pool.setLoginTimeout(REPORTING_TIMEOUT);

                // Try to connect
                try (Connection connection = getConnection()) {
                    connection.isValid(10);
                }
            } catch (PropertyVetoException | SQLException e) {
                throw new ReportingException(e);
            }
        }

        */
/**
         * Creates database
         *//*

        public void createDatabase(final List<VirtualFile> tables) {
            try (var connection = REPORTING.getConnection(); var statement = connection.createStatement()) {
                for (var table : tables) {
                    statement.executeUpdate(FileUtils.readFileToString(table.getRealFile(), StandardCharsets.UTF_8));
                }
            } catch (Exception e) {
                Logger.error(e, e.getMessage());
            }
        }

        */
/**
         * Drops database
         *//*

        public void dropDatabase() {
            try (var connection = REPORTING.getConnection(); var statement = connection.createStatement()) {
                statement.executeUpdate("DROP DATABASE IF EXISTS " + database);
            } catch (Exception e) {
                Logger.error(e, e.getMessage());
            }
        }

        */
/**
         * Checks database
         *//*

        public boolean checkDatabase() {
            return false;
        }

        private static class ReportingBuilder extends AbstractPool.Builder<ReportingPool> {
            private String database = APPLICATION_NAME;
            private boolean autocommit;
            private String user;
            private String password;

            private ReportingBuilder(final String host, final int port) {
                super(host, port);
            }

            private ReportingBuilder database(final String database) {
                this.database = database;
                return this;
            }

            private ReportingBuilder autocommit(final boolean autocommit) {
                this.autocommit = autocommit;
                return this;
            }

            private ReportingBuilder user(final String user) {
                this.user = user;
                return this;
            }

            private ReportingBuilder password(final String password) {
                this.password = password;
                return this;
            }

            protected ReportingPool build() {
                return new ReportingPool(this);
            }
        }
    }

    */
/**
     * Shutdown persistence connection
     *
     * @param type The persistence connection to shutdown
     *//*

    public static void shutdown() {
        REPORTING.shutdown();
    }
}
*/
