package com.ms.ssw.backend.controller;

import com.ms.ssw.backend.model.Employee;
import com.ms.ssw.backend.model.EmployeeDTO;
import com.ms.ssw.backend.model.SchedulePageResponseDTO;
import com.ms.ssw.backend.model.ScheduleUpdateRequest;
import com.ms.ssw.backend.service.ScheduleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/weekly")
    public SchedulePageResponseDTO getWeeklyEmployeeDetails(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        // Если даты нет — берём сегодня
        if (date == null) {
            date = LocalDate.now();
        }

        // Приводим к понедельнику той недели
        LocalDate startOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        // Передаём строго понедельник
        return scheduleService.getFullSchedulePageForWeek(date);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateSchedules(@RequestBody List<ScheduleUpdateRequest> requestList) {
        scheduleService.updateSchedules(requestList);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDTO employee) {
        scheduleService.addNewEmployee(employee);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        scheduleService.deleteEmployeeById(employeeId);
        return ResponseEntity.ok().build();
    }
}

