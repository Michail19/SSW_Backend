package com.ms.ssw.backend.model;


public class WeekScheduleDTO {
    private Long id;

    private DayScheduleDTO monday;
    private DayScheduleDTO tuesday;
    private DayScheduleDTO wednesday;
    private DayScheduleDTO thursday;
    private DayScheduleDTO friday;
    private DayScheduleDTO saturday;
    private DayScheduleDTO sunday;

    public WeekScheduleDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayScheduleDTO getMonday() {
        return monday;
    }

    public void setMonday(DayScheduleDTO monday) {
        this.monday = monday;
    }

    public DayScheduleDTO getTuesday() {
        return tuesday;
    }

    public void setTuesday(DayScheduleDTO tuesday) {
        this.tuesday = tuesday;
    }

    public DayScheduleDTO getWednesday() {
        return wednesday;
    }

    public void setWednesday(DayScheduleDTO wednesday) {
        this.wednesday = wednesday;
    }

    public DayScheduleDTO getThursday() {
        return thursday;
    }

    public void setThursday(DayScheduleDTO thursday) {
        this.thursday = thursday;
    }

    public DayScheduleDTO getFriday() {
        return friday;
    }

    public void setFriday(DayScheduleDTO friday) {
        this.friday = friday;
    }

    public DayScheduleDTO getSaturday() {
        return saturday;
    }

    public void setSaturday(DayScheduleDTO saturday) {
        this.saturday = saturday;
    }

    public DayScheduleDTO getSunday() {
        return sunday;
    }

    public void setSunday(DayScheduleDTO sunday) {
        this.sunday = sunday;
    }
}
