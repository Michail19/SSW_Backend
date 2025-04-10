package com.ms.ssw.backend.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "project_id")
    private List<EmployeeLess> employees;

    // Конструкторы, геттеры и сеттеры

    public Project(String name, List<EmployeeLess> employees) {
        this.name = name;
        this.employees = employees;
    }

    public Project() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeLess> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeLess> employees) {
        this.employees = employees;
    }
}
