package com.example.helloworldapi.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBStudentsConnection {
    private static DBStudentsConnection instanceConnection;
    private Connection conn;

    private DBStudentsConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/stud", "postgres", "postgres");
            System.out.println("CONNECTED");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBStudentsConnection getInstanceConnection() {
        if (instanceConnection == null) {
            instanceConnection = new DBStudentsConnection();
        }
        return instanceConnection;
    }

    public Connection getConn() {
        return conn;
    }
}
