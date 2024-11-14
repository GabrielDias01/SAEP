package com.dias.dao;

import com.dias.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {

    // Método para adicionar um usuário no banco de dados (com senha criptografada)
    public void addUser(User user, String senha) {
        String senhaCriptografada = BCrypt.hashpw(senha, BCrypt.gensalt());

        String sql = "INSERT INTO users (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, senhaCriptografada);  // Armazenando a senha criptografada

            statement.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar o usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para buscar um usuário por email
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("nome");
                    String emailFound = resultSet.getString("email");
                    String senha = resultSet.getString("senha");
                    return new User(id, name, emailFound, senha); // Passando a senha também
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null se o usuário não for encontrado
    }

    // Método para verificar se o usuário existe apenas pelo email
    public boolean userExistsByEmail(String email) {
        String sql = "SELECT 1 FROM users WHERE email = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Retorna true se o usuário existir
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Retorna false se o usuário não for encontrado
    }

    // Método para realizar login
    public boolean login(String email, String senha) {
        User user = getUserByEmail(email);
        if (user != null) {
            return checkPassword(user, senha);  // Verifica se a senha fornecida corresponde à senha armazenada
        }
        return false;
    }

    // Método para verificar a senha do usuário
    public boolean checkPassword(User user, String senha) {
        String senhaArmazenada = user.getSenha();
        return BCrypt.checkpw(senha, senhaArmazenada); // Verifica se a senha fornecida corresponde à armazenada
    }

    // Método para recuperar todos os usuários
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                users.add(new User(id, name, email, senha));  // Adiciona os usuários encontrados à lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;  // Retorna a lista de usuários
    }
}
