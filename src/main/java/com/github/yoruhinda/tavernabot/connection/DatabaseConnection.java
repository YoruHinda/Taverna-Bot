package com.github.yoruhinda.tavernabot.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection createConnection() {
        String url = System.getenv("DATABASE_URL");
        String password = System.getenv("DATABASE_PASSWORD");
        String user = System.getenv("DATABASE_USER");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, password, user);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
