package com.ms.ssw.backend.model;

import jakarta.persistence.*;

@Entity
public class EmployeeLess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fio;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    // Конструкторы, геттеры и сеттеры

    public EmployeeLess(String fio) {
        this.fio = fio;
    }

    public EmployeeLess() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
