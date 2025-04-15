package com.ms.ssw.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "day_schedule")
public class DaySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start") // Соответствует столбцу в БД
    private String start;

    @Column(name = "end") // Соответствует столбцу в БД
    private String end;

    public DaySchedule(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public DaySchedule() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
