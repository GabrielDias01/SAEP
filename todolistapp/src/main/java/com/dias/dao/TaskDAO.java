package com.dias.dao;

import com.dias.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    // Método para salvar (adicionar) uma tarefa
    public void save(Task task) {
        String sql = "INSERT INTO tasks (descricao, setor, prioridade, data_cadastro, status, user_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Configura os parâmetros da consulta de inserção
            stmt.setString(1, task.getDescricao());
            stmt.setString(2, task.getSetor());
            stmt.setString(3, task.getPrioridade());
            stmt.setDate(4, java.sql.Date.valueOf(task.getDataCadastro())); // Converte LocalDate para Date
            stmt.setString(5, task.getStatus());
            stmt.setInt(6, task.getUserId()); // Associa a tarefa ao user_id

            // Executa a atualização (inserção)
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao salvar a tarefa. Mensagem: " + e.getMessage());
            e.printStackTrace(); // Loga o erro completo para diagnóstico
        }
    }

    // Método para recuperar todas as tarefas de um usuário específico
    public List<Task> getTasksByUserId(int userId) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE user_id = ?";  // Filtro por user_id

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);  // Filtra as tarefas do usuário logado
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Cria a tarefa a partir dos dados retornados do banco
                    Task task = new Task(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("descricao"),
                            rs.getString("setor"),
                            rs.getString("prioridade"),
                            rs.getDate("data_cadastro").toLocalDate(),  // Converte Date para LocalDate
                            rs.getString("status")
                    );
                    tasks.add(task);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao recuperar as tarefas do usuário " + userId + ". Mensagem: " + e.getMessage());
            e.printStackTrace(); // Loga o erro completo para diagnóstico
        }
        return tasks;
    }

    // Método para atualizar uma tarefa
    public void updateTask(Task task) {
        String sql = "UPDATE tasks SET descricao = ?, setor = ?, prioridade = ?, status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Configura os parâmetros da consulta de atualização
            stmt.setString(1, task.getDescricao());
            stmt.setString(2, task.getSetor());
            stmt.setString(3, task.getPrioridade());
            stmt.setString(4, task.getStatus());
            stmt.setInt(5, task.getId()); // Atualiza a tarefa com base no ID

            // Executa a atualização
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar a tarefa com ID " + task.getId() + ". Mensagem: " + e.getMessage());
            e.printStackTrace(); // Loga o erro completo para diagnóstico
        }
    }

    // Método para deletar uma tarefa
    public void deleteTask(int taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Configura o parâmetro da consulta de exclusão
            stmt.setInt(1, taskId);

            // Executa a exclusão
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir a tarefa com ID " + taskId + ". Mensagem: " + e.getMessage());
            e.printStackTrace(); // Loga o erro completo para diagnóstico
        }
    }
}
