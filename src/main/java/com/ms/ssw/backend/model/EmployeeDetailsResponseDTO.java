package com.ms.ssw.backend.model;

public class EmployeeDetailsResponseDTO {

    private long id;
    private String fio;
    private String projects;
    private WeekScheduleDTO weekSchedule;

    public EmployeeDetailsResponseDTO() {}

    public EmployeeDetailsResponseDTO(long id, String fio, String projects, WeekScheduleDTO weekSchedule) {
        this.id = id;
        this.fio = fio;
        this.projects = projects;
        this.weekSchedule = weekSchedule;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public WeekScheduleDTO getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(WeekScheduleDTO weekSchedule) {
        this.weekSchedule = weekSchedule;
    }
}
