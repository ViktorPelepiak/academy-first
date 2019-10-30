package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static String username = "postgres";
    private static String password = "root";
    private static String connectionURL = "jdbc:postgresql://localhost:5432/schedule";

    private static Connection connection = null;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
//        Class.forName("org.postgresql.Driver");
        if (connection == null){
            connection = DriverManager.getConnection(connectionURL, username, password);
        }
        return connection;
    }
}
