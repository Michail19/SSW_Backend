package com.ms.ssw.backend.dto;

public class EmployeeDTO {
    private int id;
    private String fio;

    // Конструктор
    public EmployeeDTO(int id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
