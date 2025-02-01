package com.shop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
    public static final Connection CONNECTION = DbConnect.getConnection();

    private static Connection getConnection() {
        try {
            String url = "jdbc:postgresql://localhost:5432/grocery";
            String user = "postgres";
            String password = "";

            return DriverManager.getConnection(url, user, password);
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error during connecting to the db");
        }
        return null;
    }
}
