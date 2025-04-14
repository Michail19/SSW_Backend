package com.ms.ssw.backend.model;

public class EmployeeLessDTO {
    private long id;
    private String fio;

    // Конструктор
    public EmployeeLessDTO(long id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    // Геттеры и сеттеры
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
