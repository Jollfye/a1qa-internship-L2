package db;

import lombok.experimental.UtilityClass;
import utilities.configuration.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@UtilityClass
public class DbConnection {
    private static Connection connection;
    private static final String DB_URL = Configuration.getDbUrl();
    private static final String DB_USERNAME = Configuration.getDbUser();
    private static final String DB_PASSWORD = Configuration.getDbPassword();

    public static Connection get() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            } catch (SQLException e) {
                throw new IllegalArgumentException(
                        String.format("Connection to database %1$s was not established", DB_URL), e);
            }
        }
        return connection;
    }

    public static void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new IllegalStateException(
                        String.format("Connection to database %1$s was not closed", DB_URL), e);
            }
            connection = null;
        }
    }
}
