package com.pi.prontvita.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String descricao;

    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Usuario paciente;

    public Prontuario() {}

    public Prontuario(String descricao, LocalDate data, Usuario paciente) {
        this.descricao = descricao;
        this.data = data;
        this.paciente = paciente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Usuario getPaciente() {
        return paciente;
    }

    public void setPaciente(Usuario paciente) {
        this.paciente = paciente;
    }
}