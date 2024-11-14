package com.dias.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.dias.controller.UserController;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroUsuarioView extends JFrame {
    private JTextField nomeField, emailField;
    private JPasswordField senhaField; // Usando JPasswordField para capturar a senha
    private JButton cadastrarButton;
    private UserController userController;

    public CadastroUsuarioView() {
        userController = new UserController();
        setTitle("Cadastro de Usuário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField(); // Campo de senha

        cadastrarButton = new JButton("Cadastrar");

        add(nomeLabel);
        add(nomeField);
        add(emailLabel);
        add(emailField);
        add(senhaLabel);
        add(senhaField);
        add(cadastrarButton);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarUsuario();
            }
        });

        setVisible(true);
    }

    private void cadastrarUsuario() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String senha = new String(senhaField.getPassword());  // Obtendo a senha do campo JPasswordField

        // Validação dos campos
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.");
            return;
        }

        // Validação simples de formato de email
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email inválido. Por favor, insira um email válido.");
            return;
        }

        // Chama o controller para o cadastro do usuário
        try {
            userController.cadastrarUsuario(nome, email, senha);
            JOptionPane.showMessageDialog(this, "Cadastro concluído com sucesso.");
            dispose();  // Fecha a tela de cadastro

            // Exibe a tela de tarefas (ToDoListView) após o cadastro
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    // O userId precisa ser obtido do controlador ou retornado após o cadastro.
                    // Se você tiver um método para obter o userId após o cadastro, use-o.
                    int userId = userController.getUserIdByEmail(email); // Exemplo de como pegar o userId.

                    // Agora passa o userId para o construtor da ToDoListView
                    new ToDoListView(userId);  // Passando o userId para o construtor da ToDoListView
                }
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar usuário: " + ex.getMessage());
        }
    }

    // Função de validação de email simples
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
