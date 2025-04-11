package com.ms.ssw.backend.model;

import java.util.Map;

public class WeekScheduleDTO {
    private Map<String, ScheduleDTO> days;

    public WeekScheduleDTO(Map<String, ScheduleDTO> days) {
        this.days = days;
    }

    public Map<String, ScheduleDTO> getDays() {
        return days;
    }

    public void setDays(Map<String, ScheduleDTO> days) {
        this.days = days;
    }
}
