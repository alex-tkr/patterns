package tokar.patterns;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/displays";
    private static final String USER = "root";
    private static final String PASSWORD = "1111";
    public static Connection getConnection(boolean autocommit) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        connection.setAutoCommit(autocommit);
        if (!autocommit) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        }
        return connection;
    }
    public static Connection getConnection() throws SQLException {
        return getConnection(true);
    }
}