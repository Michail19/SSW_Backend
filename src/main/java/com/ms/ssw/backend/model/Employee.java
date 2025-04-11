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
    @JoinTable (name = "projects")
    private List<String> projects = new ArrayList<>();

    private WeekScheduleDTO weekSchedule;

    public Employee(long id, String fio, ArrayList<String> projects, WeekScheduleDTO weekSchedule) {
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

    public ArrayList<String> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<String> projects) {
        this.projects = projects;
    }

    public WeekScheduleDTO getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(WeekScheduleDTO weekSchedule) {
        this.weekSchedule = weekSchedule;
    }
}
