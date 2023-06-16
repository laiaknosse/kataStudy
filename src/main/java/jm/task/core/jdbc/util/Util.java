package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;


public class Util {


    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();
        properties.put(AvailableSettings.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.put(AvailableSettings.URL, "jdbc:mysql://localhost:3306/users");
        properties.put(AvailableSettings.USER, "root");
        properties.put(AvailableSettings.PASS, "admin");
        properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        //properties.put(HBM2DDL_AUTO, "update");

        return configuration
                .setProperties(properties)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
}
