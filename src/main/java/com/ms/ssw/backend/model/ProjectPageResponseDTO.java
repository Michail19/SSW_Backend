package com.ms.ssw.backend.model;

import java.util.List;
import java.util.Map;

public class ProjectPageResponseDTO {
    private String currentWeek;
    private Map<String, List<EmployeeLessDTO>> projects; // Ключ - название проекта

    public ProjectPageResponseDTO(String currentWeek, Map<String, List<EmployeeLessDTO>> projects) {
        this.currentWeek = currentWeek;
        this.projects = projects;
    }

    public String getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(String currentWeek) {
        this.currentWeek = currentWeek;
    }

    public Map<String, List<EmployeeLessDTO>> getEmployees() {
        return projects;
    }

    public void setEmployees(Map<String, List<EmployeeLessDTO>> employees) {
        this.projects = employees;
    }
}
