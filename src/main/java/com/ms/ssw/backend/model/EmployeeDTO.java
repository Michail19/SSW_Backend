package com.ms.ssw.backend.model;

public class EmployeeDTO {
    private long id;
    private EmployeeLessDTO employeeLess;
    private WeekScheduleDTO weekSchedule;

    public EmployeeDTO(long id, EmployeeLessDTO employeeLess, WeekScheduleDTO weekSchedule) {
        this.id = id;
        this.employeeLess = employeeLess;
        this.weekSchedule = weekSchedule;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EmployeeLessDTO getEmployeeLess() {
        return employeeLess;
    }

    public void setEmployeeLess(EmployeeLessDTO employeeLess) {
        this.employeeLess = employeeLess;
    }

    public WeekScheduleDTO getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(WeekScheduleDTO weekSchedule) {
        this.weekSchedule = weekSchedule;
    }
}
