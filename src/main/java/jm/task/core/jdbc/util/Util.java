package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
     private static final String connectionUrl = "jdbc:mysql://localhost:3306/katadb";
     private static final String username = "root";
     private static final String password = "root";

    public static Connection connection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(connectionUrl, username, password);
    }
}


