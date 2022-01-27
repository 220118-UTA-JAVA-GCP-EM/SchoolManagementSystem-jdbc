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
        String username = "postgres";
        String password = "p4ssw0rd";
        return DriverManager.getConnection(url,username, password);
    }


}
