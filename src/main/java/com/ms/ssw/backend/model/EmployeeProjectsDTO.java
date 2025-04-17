package com.ms.ssw.backend.model;

import java.util.List;

public class EmployeeProjectsDTO {
    private String action;
    private List<EmployeeLessDTO> fio;
    private String project;

    // Конструктор
    public EmployeeProjectsDTO(String action, List<EmployeeLessDTO> fio, String project) {
        this.action = action;
        this.fio = fio;
        this.project = project;
    }

    // Геттеры и сеттеры
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<EmployeeLessDTO> getFio() {
        return fio;
    }

    public void setFio(List<EmployeeLessDTO> fio) {
        this.fio = fio;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}