package com.example.inventoryrestapi;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/REST_API_OOP";
    private static final String user = "postgres";
    private static final String password = "S27052007s";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, user, password);
    }

}
