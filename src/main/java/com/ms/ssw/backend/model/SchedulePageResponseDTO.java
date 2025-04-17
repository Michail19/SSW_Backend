package com.ms.ssw.backend.model;

import java.util.List;

public class SchedulePageResponseDTO {
    private String currentWeek;
    private List<EmployeeDetailsResponseDTO> employees;

    public SchedulePageResponseDTO(String currentWeek, List<EmployeeDetailsResponseDTO> employees) {
        this.currentWeek = currentWeek;
        this.employees = employees;
    }

    public String getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(String currentWeek) {
        this.currentWeek = currentWeek;
    }

    public List<EmployeeDetailsResponseDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDetailsResponseDTO> employees) {
        this.employees = employees;
    }
}
