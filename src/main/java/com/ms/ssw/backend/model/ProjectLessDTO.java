package com.ms.ssw.backend.model;

import java.util.List;

public class ProjectLessDTO {
    private String name;
    private List<EmployeeLessDTO> employees;  // Изменяем на список EmployeeDTO

    // Конструктор
    public ProjectLessDTO(String name, List<EmployeeLessDTO> employees) {
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

    public List<EmployeeLessDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeLessDTO> employees) {
        this.employees = employees;
    }
}
