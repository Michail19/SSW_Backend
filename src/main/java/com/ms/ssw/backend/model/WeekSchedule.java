package com.ms.ssw.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "week_schedule")
public class WeekSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "monday_id") // Явно укажите имя столбца внешнего ключа
    private DaySchedule monday;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tuesday_id")
    private DaySchedule tuesday;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wednesday_id")
    private DaySchedule wednesday;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "thursday_id")
    private DaySchedule thursday;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "friday_id")
    private DaySchedule friday;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "saturday_id")
    private DaySchedule saturday;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sunday_id")
    private DaySchedule sunday;

    @Column(name = "start_of_week")
    private LocalDate startOfWeek; // для понимания, к какой неделе относится

    public WeekSchedule(DaySchedule monday,
                        DaySchedule tuesday,
                        DaySchedule wednesday,
                        DaySchedule thursday,
                        DaySchedule friday,
                        DaySchedule saturday,
                        DaySchedule sunday,
                        LocalDate startOfWeek) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
        this.startOfWeek = startOfWeek;
    }

    public WeekSchedule() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DaySchedule getMonday() {
        return monday;
    }

    public void setMonday(DaySchedule monday) {
        this.monday = monday;
    }

    public DaySchedule getTuesday() {
        return tuesday;
    }

    public void setTuesday(DaySchedule tuesday) {
        this.tuesday = tuesday;
    }

    public DaySchedule getWednesday() {
        return wednesday;
    }

    public void setWednesday(DaySchedule wednesday) {
        this.wednesday = wednesday;
    }

    public DaySchedule getThursday() {
        return thursday;
    }

    public void setThursday(DaySchedule thursday) {
        this.thursday = thursday;
    }

    public DaySchedule getFriday() {
        return friday;
    }

    public void setFriday(DaySchedule friday) {
        this.friday = friday;
    }

    public DaySchedule getSaturday() {
        return saturday;
    }

    public void setSaturday(DaySchedule saturday) {
        this.saturday = saturday;
    }

    public DaySchedule getSunday() {
        return sunday;
    }

    public void setSunday(DaySchedule sunday) {
        this.sunday = sunday;
    }

    public LocalDate getStartOfWeek() {
        return startOfWeek;
    }

    public void setStartOfWeek(LocalDate startOfWeek) {
        this.startOfWeek = startOfWeek;
    }
}
