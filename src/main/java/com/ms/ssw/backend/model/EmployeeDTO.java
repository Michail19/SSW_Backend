package com.ms.ssw.backend.model;

public class EmployeeDTO {
//    private long id;
    private String fio;
    private WeekScheduleDTO weekSchedule;

    public EmployeeDTO(String fio, WeekScheduleDTO weekSchedule) {
        this.fio = fio;
        this.weekSchedule = weekSchedule;
    }

//    public EmployeeDTO(long id, String fio, WeekScheduleDTO weekSchedule) {
//        this.id = id;
//        this.fio = fio;
//        this.weekSchedule = weekSchedule;
//    }

//    public EmployeeDTO(long id, String fio) {
//        this.id = id;
//        this.fio = fio;
//    }

//    public long getId() {
//        return id;
//    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public WeekScheduleDTO getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(WeekScheduleDTO weekSchedule) {
        this.weekSchedule = weekSchedule;
    }
}
