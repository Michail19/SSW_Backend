package com.ms.ssw.backend.model;

import java.util.ArrayList;

public class Project {
    private String name;
    private ArrayList<EmployeeLess> employees;

    public Project(String name, ArrayList<EmployeeLess> employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<EmployeeLess> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<EmployeeLess> employees) {
        this.employees = employees;
    }
}
