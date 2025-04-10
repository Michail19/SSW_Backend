package com.ms.ssw.backend.model;

import java.util.List;

public class ProjectDTO {
    private String name;
    private List<EmployeeDTO> employees;  // Изменяем на список EmployeeDTO

    // Конструктор
    public ProjectDTO(String name, List<EmployeeDTO> employees) {
        this.name = name;
        this.employees = employees;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }
}
