package ua.com.okonsergei.utils;


import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class FlywayMigration {

    private static final String url;
    private static final String username;
    private static final String password;

    static {
        Properties props = new Properties();
        try (InputStream in = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("database.properties")) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = props.getProperty("url");
        username = props.getProperty("username");
        password = props.getProperty("password");
    }

    public static void runFlyWay() {
        Flyway flyway = Flyway.configure()
                .dataSource(url, username, password)
                .load();
        flyway.migrate();
    }
}
