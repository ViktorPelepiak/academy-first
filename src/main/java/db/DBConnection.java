package db;

import org.postgresql.ds.PGConnectionPoolDataSource;

import javax.sql.ConnectionPoolDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DBConnection {
//    private static final String PROPERTIES = "D:\\git_academy\\src\\main\\resources\\application.properties";
//    private static final String PROPERTIES = "application.properties";
    private static ConnectionPoolDataSource poolDataSource;

    private DBConnection() {
    }

    static {
        try {
            reconnect();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void reconnect() throws IOException, ClassNotFoundException {
        PGConnectionPoolDataSource source = new PGConnectionPoolDataSource();
        Properties properties = new Properties();
//        PropertyResourceBundle bundle = (PropertyResourceBundle)PropertyResourceBundle.getBundle("application");
//        Class.forName("org.postgresql.Driver");
        final String SERVER_NAME = "localhost";
        final String DATABASE_NAME = "vip_schedule";
        final String DATABASE_USERNAME = "postgres";
        final String DATABASE_PASSWORD = "root";
        final String APP_NAME = "ViPSchedule";

//        properties.load(new FileInputStream(PROPERTIES));
//        properties.load(DBConnection.class.getClassLoader().getResourceAsStream(PROPERTIES));
//        source.setServerName(properties.getProperty("jdbc.server.name"));
//        source.setDatabaseName(properties.getProperty("jdbc.database.name"));
//        source.setUser(properties.getProperty("jdbc.database.username"));
//        source.setPassword(properties.getProperty("jdbc.database.password"));
//        source.setApplicationName(properties.getProperty("application.name"));
        source.setApplicationName(APP_NAME);
        source.setServerName(SERVER_NAME);
        source.setDatabaseName(DATABASE_NAME);
        source.setUser(DATABASE_USERNAME);
        source.setPassword(DATABASE_PASSWORD);

        poolDataSource = source;
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = poolDataSource.getPooledConnection().getConnection();
        try {
            if (Objects.isNull(connection)) throw new NullPointerException();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
