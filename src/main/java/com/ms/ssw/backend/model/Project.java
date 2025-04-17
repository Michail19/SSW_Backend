package com.ms.ssw.backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_name")  // Соответствует столбцу в БД
    private String name;

    @ManyToMany(mappedBy = "projects")  // Указываем на поле в Employee
    private List<Employee> employees = new ArrayList<>();

    // Конструкторы, геттеры и сеттеры

    public Project(String name, List<Employee> employees) {
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
