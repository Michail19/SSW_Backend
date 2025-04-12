package com.ms.ssw.backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fio;

    @OneToMany
    @JoinTable (name = "projects_table")
    private List<Project> projects;

    private WeekScheduleDTO weekSchedule;

    public Employee(String fio, List<Project> projects, WeekScheduleDTO weekSchedule) {
        this.fio = fio;
        this.projects = projects;
        this.weekSchedule = weekSchedule;
    }

    public Employee() {

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

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public WeekScheduleDTO getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(WeekScheduleDTO weekSchedule) {
        this.weekSchedule = weekSchedule;
    }
}
