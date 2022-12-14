package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection connection = null;

    public static Connection getConnection() {

        if (connection == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("url");
                connection = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }

        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    private static Properties loadProperties() {
        try (FileInputStream file = new FileInputStream(".env")){

            Properties props = new Properties();
            props.load(file);

//            props.setProperty("user", System.getenv("USER"));
//            props.setProperty("password", System.getenv("PASSWORD"));
//            props.setProperty("url", System.getenv("URL"));
//            props.setProperty("useSSL", System.getenv("USESSL"));

            return props;

        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

}
