package com.ms.ssw.backend.model;

public class Schedule {
    private String start;
    private String end;

    public Schedule(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
