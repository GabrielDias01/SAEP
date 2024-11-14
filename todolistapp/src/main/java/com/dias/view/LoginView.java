package com.dias.view;

import com.dias.controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JTextField emailField;
    private JPasswordField senhaField;
    private JButton loginButton, cadastroButton;
    private UserController userController;

    public LoginView() {
        userController = new UserController();

        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2)); // Alterando para 4 linhas e 2 colunas

        // Componentes para o formulário de login
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField();

        loginButton = new JButton("Entrar");
        cadastroButton = new JButton("Cadastre-se");

        // Adicionando componentes ao layout
        add(emailLabel);
        add(emailField);
        add(senhaLabel);
        add(senhaField);
        add(loginButton);
        add(cadastroButton);

        // Ação do botão de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUsuario();
            }
        });

        // Ação do botão de cadastro (Redireciona para a tela de cadastro)
        cadastroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroUsuarioView();
                dispose();  // Fecha a tela de login
            }
        });

        setVisible(true);
    }

    private void loginUsuario() {
        String email = emailField.getText();
        char[] senhaArray = senhaField.getPassword();
        String senha = new String(senhaArray);

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.");
            return;
        }

        boolean loginSucesso = userController.login(email, senha);  // Verificando o login

        if (loginSucesso) {
            // Obtém o userId
            int userId = userController.getUserIdByEmail(email);

            if (userId != -1) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
                dispose();  // Fecha a tela de login
                new ToDoListView(userId);  // Passa o userId para a tela de tarefas
            } else {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Email ou senha incorretos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView());
    }
}
