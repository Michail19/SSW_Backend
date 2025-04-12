package com.ms.ssw.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class WeekSchedule {

    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
