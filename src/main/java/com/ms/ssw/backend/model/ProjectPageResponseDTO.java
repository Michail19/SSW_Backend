package com.ms.ssw.backend.model;

import java.util.List;

public class ProjectPageResponseDTO {
    private String currentWeek;
    private List<EmployeeLessDTO> projects;

    public ProjectPageResponseDTO(String currentWeek, List<EmployeeLessDTO> projects) {
        this.currentWeek = currentWeek;
        this.projects = projects;
    }

    public String getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(String currentWeek) {
        this.currentWeek = currentWeek;
    }

    public List<EmployeeLessDTO> getEmployees() {
        return projects;
    }

    public void setEmployees(List<EmployeeLessDTO> employees) {
        this.projects = employees;
    }
}
