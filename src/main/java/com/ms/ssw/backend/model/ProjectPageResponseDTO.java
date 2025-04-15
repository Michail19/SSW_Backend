package com.ms.ssw.backend.model;

import java.util.List;

public class ProjectPageResponseDTO {
    private String currentWeek;
    private List<Project> projects;

    public ProjectPageResponseDTO(String currentWeek, List<Project> projects) {
        this.currentWeek = currentWeek;
        this.projects = projects;
    }

    public String getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(String currentWeek) {
        this.currentWeek = currentWeek;
    }

    public List<Project> getEmployees() {
        return projects;
    }

    public void setEmployees(List<Project> employees) {
        this.projects = employees;
    }
}
