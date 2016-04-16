package by.iba.gomel.connectiondb;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.iba.gomel.Constants;

/**
 * This class contains method getConnection that gets connection from Connection pool from
 * Websphere.
 */
public class ConnectionDB2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionDB2.class);

    private ConnectionDB2() {
        // empty constructor.
    }

    /**
     * 
     * @return active connection.
     * @throws SQLException
     *             exception if connection wasn't get.
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        InitialContext ctx = null;
        DataSource ds = null;
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(Constants.LOGIC_NAME_DB);
            connection = ds.getConnection();
        } catch (final NamingException e) {
            ConnectionDB2.LOGGER.error(Constants.NAMING_EXCEPTION, e);
        }
        return connection;
    }

}
