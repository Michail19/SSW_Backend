package com.ms.ssw.backend.model;

import java.time.LocalDate;

public class ScheduleUpdateRequest {
    private Long employeeId;
    private LocalDate weekStart;
    private WeekScheduleDTO schedule;

    public ScheduleUpdateRequest(Long employeeId, LocalDate weekStart, WeekScheduleDTO schedule) {
        this.employeeId = employeeId;
        this.weekStart = weekStart;
        this.schedule = schedule;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(LocalDate weekStart) {
        this.weekStart = weekStart;
    }

    public WeekScheduleDTO getSchedule() {
        return schedule;
    }

    public void setSchedule(WeekScheduleDTO schedule) {
        this.schedule = schedule;
    }
}
