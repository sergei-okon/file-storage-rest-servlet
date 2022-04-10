package ua.com.okonsergei.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration()
                .configure("Hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }
}


