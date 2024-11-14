package com.dias.model;

import java.time.LocalDate;

public class Task {
    private int id;
    private int userId;
    private String descricao;
    private String setor;
    private String prioridade;
    private LocalDate dataCadastro;
    private String status;

    // Construtores
    public Task(String descricao, String setor, String prioridade, LocalDate dataCadastro, String status) {
        this.descricao = descricao;
        this.setor = setor;
        this.prioridade = prioridade;
        this.dataCadastro = dataCadastro;
        this.status = status;
    }

    public Task(int id, int userId, String descricao, String setor, String prioridade, LocalDate dataCadastro,
            String status) {
        this.id = id;
        this.userId = userId;
        this.descricao = descricao;
        this.setor = setor;
        this.prioridade = prioridade;
        this.dataCadastro = dataCadastro;
        this.status = status;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Descrição: " + descricao + " | Setor: " + setor + " | Prioridade: " + prioridade
                + " | Status: " + status + " | Data: " + dataCadastro;
    }
}
