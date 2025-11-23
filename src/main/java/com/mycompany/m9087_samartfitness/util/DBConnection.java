package com.mycompany.m9087_samartfitness.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/M9087_smartfitnessdb"; // DB name
    private static final String USER = "root";   // change if needed
    private static final String PASSWORD = "root";   // MySQL password here
    private static Connection connection = null;

    private DBConnection() {
        // private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Database connected successfully!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Database connection error: " + e.getMessage());
        }
        return connection;
    }
}
