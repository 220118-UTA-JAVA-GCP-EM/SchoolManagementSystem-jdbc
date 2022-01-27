package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://35.188.99.119:5432/postgres";
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASS");
        return DriverManager.getConnection(url,username, password);
    }


}
