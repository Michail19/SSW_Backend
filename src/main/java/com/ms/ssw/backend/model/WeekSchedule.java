package com.ms.ssw.backend.model;

import java.util.Map;

public class WeekSchedule {
    private Map<String, Schedule> days;

    public WeekSchedule(Map<String, Schedule> days) {
        this.days = days;
    }

    public Map<String, Schedule> getDays() {
        return days;
    }

    public void setDays(Map<String, Schedule> days) {
        this.days = days;
    }
}
