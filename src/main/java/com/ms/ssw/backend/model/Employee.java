package com.ms.ssw.backend.model;

import java.util.ArrayList;

public class Employee {
    private int id;
    private String fio;
    private ArrayList<String> projects;
    private WeekSchedule weekSchedule;

    public Employee(int id, String fio, ArrayList<String> projects, WeekSchedule weekSchedule) {
        this.id = id;
        this.fio = fio;
        this.projects = projects;
        this.weekSchedule = weekSchedule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public WeekSchedule getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(WeekSchedule weekSchedule) {
        this.weekSchedule = weekSchedule;
    }
}
