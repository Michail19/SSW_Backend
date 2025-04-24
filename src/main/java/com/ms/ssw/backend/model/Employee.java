package com.ms.ssw.backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String fio;  // Соответствует столбцу в БД

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WeekSchedule> weekSchedules = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "employee_projects",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Геттеры и сеттеры

    public Employee() {

    }

    public List<WeekSchedule> getWeekSchedules() {
        return weekSchedules;
    }

    public void setWeekSchedules(List<WeekSchedule> weekSchedules) {
        this.weekSchedules = weekSchedules;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
