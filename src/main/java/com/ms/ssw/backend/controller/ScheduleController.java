package com.ms.ssw.backend.controller;

import com.ms.ssw.backend.config.CustomUserDetails;
import com.ms.ssw.backend.model.EmployeeDTO;
import com.ms.ssw.backend.model.SchedulePageResponseDTO;
import com.ms.ssw.backend.model.ScheduleUpdateRequest;
import com.ms.ssw.backend.service.ScheduleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public SchedulePageResponseDTO getWeeklyEmployeeDetails(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Authentication authentication
    ) {
        if (date == null) {
            date = LocalDate.now();
        }
        LocalDate startOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId(); // ← Получили userId из токена

        return scheduleService.getFullSchedulePageForWeek(startOfWeek, userId);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateSchedules(@RequestBody List<ScheduleUpdateRequest> requestList) {
        scheduleService.updateSchedules(requestList);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addEmployee(@RequestBody EmployeeDTO employee) {
        Long id = scheduleService.addNewEmployee(employee);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        scheduleService.deleteEmployeeById(employeeId);
        return ResponseEntity.ok().build();
    }
}
