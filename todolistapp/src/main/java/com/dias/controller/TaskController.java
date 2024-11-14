package com.dias.controller;

import com.dias.dao.TaskDAO;
import com.dias.model.Task;

import java.time.LocalDate;
import java.util.List;

public class TaskController {
    private TaskDAO taskDAO;

    public TaskController() {
        taskDAO = new TaskDAO();
    }

    // Adicionar tarefa
    public void addTask(int userId, String descricao, String setor, String prioridade, String status) {
        // Criação de uma nova instância de Task com a data de cadastro atual
        Task newTask = new Task(descricao, setor, prioridade, LocalDate.now(), status);
        
        // Setando o userId (o ID do usuário que está criando a tarefa)
        newTask.setUserId(userId);
        
        // Adicionando a tarefa no banco de dados (usando o método save no DAO)
        taskDAO.save(newTask);  // Chama o método save do TaskDAO para adicionar a tarefa
    }

    // Atualizar tarefa
    public void updateTask(Task task) {
        // Atualiza a tarefa no banco de dados
        taskDAO.updateTask(task);
    }

    // Excluir tarefa
    public void deleteTask(int taskId) {
        // Exclui a tarefa no banco de dados
        taskDAO.deleteTask(taskId);
    }

    // Recuperar todas as tarefas de um usuário
    public List<Task> getAllTasks(int userId) {
        return taskDAO.getTasksByUserId(userId);
    }
}
