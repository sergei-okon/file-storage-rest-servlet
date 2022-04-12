package ua.com.okonsergei.servlet;

import ua.com.okonsergei.utils.FlywayMigration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class FlywayListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public void contextInitialized(ServletContextEvent sce) {
        FlywayMigration.runFlyWay();
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}