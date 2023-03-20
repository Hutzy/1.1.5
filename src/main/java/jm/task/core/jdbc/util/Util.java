package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.cfg.Environment;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
     private static final String connectionUrl = "jdbc:mysql://localhost:3306/katadb";
     private static final String username = "root";
     private static final String password = "root";

    public static SessionFactory getSessionFactory () {
         Configuration configuration = new Configuration();
         Properties settings = new Properties();

         settings.put(Environment.URL, connectionUrl );
         settings.put(Environment.USER, username );
         settings.put(Environment.PASS, password );
         settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver" );
         settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect" );
         settings.put(Environment.SHOW_SQL, true);
         //settings.put(Environment.HBM2DDL_AUTO, true);

        configuration.addProperties(settings).addAnnotatedClass(User.class);

         ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                 .applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
     }

    public static Connection connection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(connectionUrl, username, password);
    }
}


