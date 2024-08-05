package com.example.mecanicos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/mecanicos_db";
    private static final String USER = "root";
    private static final String PASSWORD = "1234"; // Ajusta según corresponda

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Asegúrate de que esta línea esté presente
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("No se pudo cargar el driver JDBC.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
