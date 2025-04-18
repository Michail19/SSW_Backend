package com.ms.ssw.backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeDTO {
//    private long id;
    private String fio;
    private String username;
    private String password;
    private String role;
    private WeekScheduleDTO schedule;

    public EmployeeDTO() {
    }

    @JsonCreator
    public EmployeeDTO(
            @JsonProperty("fio") String fio,
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("role") String role,
            @JsonProperty("schedule") WeekScheduleDTO schedule) {
        this.fio = fio;
        this.username = username;
        this.password = password;
        this.role = role;
        this.schedule = schedule;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return role;
    }

    public void setLevel(String role) {
        this.role = role;
    }

    public WeekScheduleDTO getWeekSchedule() {
        return schedule;
    }

    public void setWeekSchedule(WeekScheduleDTO schedule) {
        this.schedule = schedule;
    }
}
