package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String DIALECT = "org.hibernate.dialect.PostgreSQLDialect";

    public static Connection getConnectionDataBase() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            if (connection != null) {
                System.out.println("База данных подключена!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        return connection;
    }

    public static SessionFactory getSessionFactory() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.driver_class", DRIVER);
        properties.setProperty("hibernate.connection.url", URL);
        properties.setProperty("hibernate.connection.username", LOGIN);
        properties.setProperty("hibernate.connection.password", PASSWORD);
        properties.setProperty("hibernate.dialect", DIALECT);
        Configuration configuration = new Configuration()
                .addAnnotatedClass(User.class).setProperties(properties);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}