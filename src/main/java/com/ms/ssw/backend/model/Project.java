package com.ms.ssw.backend.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Project {

    String name;

    @ManyToOne
    @JoinColumn(name = "projects_table")
    private Employee employee;

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
