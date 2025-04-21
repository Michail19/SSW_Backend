package com.ms.ssw.backend.model;

public class DayScheduleDTO {

    private String start;
    private String end;

    public DayScheduleDTO() {}

    public DayScheduleDTO(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start != null ? start : "";
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end != null ? end : "";
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
