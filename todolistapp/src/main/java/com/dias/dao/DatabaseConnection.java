package com.dias.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/todolistapp";  // Banco de dados 'todolistapp'
    private static final String USER = "postgres";  // Usuário do PostgreSQL
    private static final String PASSWORD = "postgres";  // Senha do PostgreSQL

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro na conexão com o banco de dados.");
            throw e;
        }
    }
}
