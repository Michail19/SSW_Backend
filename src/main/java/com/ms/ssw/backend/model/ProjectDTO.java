package com.ms.ssw.backend.model;

public class ProjectDTO {

    String name;

    public ProjectDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
