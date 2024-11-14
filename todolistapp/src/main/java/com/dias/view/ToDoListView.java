package com.dias.view;

import com.dias.controller.TaskController;
import com.dias.model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ToDoListView extends JFrame {
    private TaskController controller;
    private JList<String> taskList;  // Usamos JList para exibir tarefas
    private DefaultListModel<String> listModel;  // Modelo de dados para o JList
    private JTextField descriptionField, departmentField, priorityField;
    private JCheckBox todoCheckBox, doingCheckBox, doneCheckBox;
    private int userId;  // Guardando o userId
    private Task selectedTask;  // Tarefa selecionada

    public ToDoListView(int userId) {
        this.userId = userId;  // Recebendo o userId
        controller = new TaskController();
        initialize();
    }

    private void initialize() {
        setTitle("To-Do List App");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Modelo de lista e JList para exibir as tarefas
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  // Permite selecionar apenas uma tarefa
        taskList.addListSelectionListener(e -> loadSelectedTask());  // Carrega tarefa selecionada

        JScrollPane listScrollPane = new JScrollPane(taskList);
        add(listScrollPane, BorderLayout.CENTER);

        // Painel para os campos de entrada de tarefas
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));  // Alterado para 8 linhas para ajustar a quantidade de campos

        panel.add(new JLabel("Descrição da Tarefa:"));
        descriptionField = new JTextField();
        panel.add(descriptionField);

        panel.add(new JLabel("Setor:"));
        departmentField = new JTextField();
        panel.add(departmentField);

        panel.add(new JLabel("Prioridade:"));
        priorityField = new JTextField();
        panel.add(priorityField);

        panel.add(new JLabel("Status:"));
        todoCheckBox = new JCheckBox("A Fazer");
        doingCheckBox = new JCheckBox("Fazendo");
        doneCheckBox = new JCheckBox("Pronto");

        // Coloca os checkboxes de status no painel
        panel.add(todoCheckBox);
        panel.add(doingCheckBox);
        panel.add(doneCheckBox);

        // Botões para adicionar, atualizar e excluir tarefas
        JButton addButton = new JButton("Adicionar Tarefa");
        JButton updateButton = new JButton("Atualizar Tarefa");
        JButton deleteButton = new JButton("Excluir Tarefa");

        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);

        add(panel, BorderLayout.NORTH);

        // Listener para o botão de adicionar tarefa
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        // Listener para o botão de atualizar tarefa
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTask();
            }
        });

        // Listener para o botão de excluir tarefa
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });

        // Atualiza a lista de tarefas
        updateTaskList();

        setVisible(true);
    }

    private void addTask() {
        String description = descriptionField.getText().trim();
        String department = departmentField.getText().trim();
        String priority = priorityField.getText().trim();
        String status = getStatusFromCheckboxes();

        // Validação dos campos de entrada
        if (description.isEmpty() || department.isEmpty() || priority.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cria e adiciona a tarefa no controlador
        controller.addTask(userId, description, department, priority, status);

        // Atualiza a lista de tarefas na tela
        updateTaskList();

        // Limpa os campos após adicionar a tarefa
        clearFields();

        // Exibe uma mensagem de sucesso
        JOptionPane.showMessageDialog(this, "Tarefa adicionada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateTask() {
        // Verifica se uma tarefa está selecionada
        if (selectedTask == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa para atualizar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String description = descriptionField.getText().trim();
        String department = departmentField.getText().trim();
        String priority = priorityField.getText().trim();
        String status = getStatusFromCheckboxes();

        // Validação dos campos de entrada
        if (description.isEmpty() || department.isEmpty() || priority.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Atualiza os atributos da tarefa selecionada
        selectedTask.setDescricao(description);
        selectedTask.setSetor(department);
        selectedTask.setPrioridade(priority);
        selectedTask.setStatus(status);

        // Atualiza a tarefa no controlador
        controller.updateTask(selectedTask);

        // Atualiza a lista de tarefas na tela
        updateTaskList();

        // Limpa os campos após atualizar a tarefa
        clearFields();

        // Exibe uma mensagem de sucesso
        JOptionPane.showMessageDialog(this, "Tarefa atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteTask() {
        // Verifica se uma tarefa está selecionada
        if (selectedTask == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Exclui a tarefa no controlador
        controller.deleteTask(selectedTask.getId());

        // Atualiza a lista de tarefas na tela
        updateTaskList();

        // Limpa os campos após excluir a tarefa
        clearFields();

        // Exibe uma mensagem de sucesso
        JOptionPane.showMessageDialog(this, "Tarefa excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateTaskList() {
        listModel.clear();  // Limpa a lista antes de adicionar as tarefas atualizadas
        List<Task> tasks = controller.getAllTasks(userId);

        // Adiciona as tarefas no modelo de lista
        for (Task task : tasks) {
            listModel.addElement(task.toString());
        }
    }

    private String getStatusFromCheckboxes() {
        if (todoCheckBox.isSelected()) {
            return "A Fazer";
        } else if (doingCheckBox.isSelected()) {
            return "Fazendo";
        } else if (doneCheckBox.isSelected()) {
            return "Pronto";
        } else {
            return "A Fazer";  // Default
        }
    }

    private void clearFields() {
        descriptionField.setText("");
        departmentField.setText("");
        priorityField.setText("");
        todoCheckBox.setSelected(false);
        doingCheckBox.setSelected(false);
        doneCheckBox.setSelected(false);
        selectedTask = null;  // Limpa a tarefa selecionada
    }

    private void loadSelectedTask() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            selectedTask = controller.getAllTasks(userId).get(index);  // Carrega a tarefa baseada no índice
            descriptionField.setText(selectedTask.getDescricao());
            departmentField.setText(selectedTask.getSetor());
            priorityField.setText(selectedTask.getPrioridade());

            // Marca o checkbox correspondente ao status
            switch (selectedTask.getStatus()) {
                case "A Fazer":
                    todoCheckBox.setSelected(true);
                    break;
                case "Fazendo":
                    doingCheckBox.setSelected(true);
                    break;
                case "Pronto":
                    doneCheckBox.setSelected(true);
                    break;
            }
        }
    }
}
