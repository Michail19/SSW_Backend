package com.ms.ssw.backend.model;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private EmployeeLess employeeData;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "week_schedule_id")
    private WeekSchedule weekSchedule;

    public Employee(EmployeeLess employeeData, WeekSchedule weekSchedule) {
        this.employeeData = employeeData;
        this.weekSchedule = weekSchedule;
    }

    public Employee() {

    }

    public WeekSchedule getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(WeekSchedule weekSchedule) {
        this.weekSchedule = weekSchedule;
    }

    public EmployeeLess getData() {
        return employeeData;
    }

    public void setData(EmployeeLess employeeData) {
        this.employeeData = employeeData;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
