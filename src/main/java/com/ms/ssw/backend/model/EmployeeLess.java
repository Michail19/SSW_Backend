package com.ms.ssw.backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class EmployeeLess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fio;

    @ManyToMany(mappedBy = "employees")
    private List<ProjectLess> projects = new ArrayList<>();

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

    public List<ProjectLess> getProjects() {
        return projects;
    }
}
