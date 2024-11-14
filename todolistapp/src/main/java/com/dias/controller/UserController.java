package com.dias.controller;

import com.dias.dao.UserDAO;
import com.dias.model.User;

public class UserController {

    private UserDAO userDAO;

    public UserController() {
        userDAO = new UserDAO();
    }

    // Método para verificar se o usuário existe e realizar o login
    public boolean login(String email, String senha) {
        return userDAO.login(email, senha);  // Verifica se o login é bem-sucedido
    }

    // Método para obter o userId com base no email
    public int getUserIdByEmail(String email) {
        User user = userDAO.getUserByEmail(email);
        return (user != null) ? user.getId() : -1;  // Retorna o userId ou -1 se não encontrar o usuário
    }

    // Método para cadastrar um novo usuário
    public boolean registerUser(User user, String senha) {
        if (userDAO.userExistsByEmail(user.getEmail())) {
            return false;  // Caso o email já exista no banco
        }
        userDAO.addUser(user, senha);
        return true;  // Cadastro bem-sucedido
    }

    // Método que realiza o cadastro do usuário
    public void cadastrarUsuario(String nome, String email, String senha) {
        // Validações para garantir que os dados não sejam nulos ou vazios
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("O email não pode ser vazio.");
        }
        if (senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser vazia.");
        }

        // Validação do formato de email
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("O email fornecido não é válido.");
        }

        // Validação da senha (mínimo de 8 caracteres)
        if (senha.length() < 8) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres.");
        }

        // Criação do objeto User com os dados fornecidos
        User newUser = new User(nome, email, senha);  // Corrigido para passar a senha também

        // Chama o método registerUser para registrar o usuário no banco de dados
        boolean isRegistered = registerUser(newUser, senha);

        // Se o cadastro não for bem-sucedido, lança uma exceção
        if (!isRegistered) {
            throw new IllegalArgumentException("Já existe um usuário com esse email.");
        }

        // Se o registro for bem-sucedido, o usuário será adicionado ao banco de dados
    }

    // Validação de email (simples)
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}
