package com.ms.ssw.backend.controller;

import com.ms.ssw.backend.model.Employee;
import com.ms.ssw.backend.model.EmployeeDTO;
import com.ms.ssw.backend.model.SchedulePageResponseDTO;
import com.ms.ssw.backend.model.ScheduleUpdateRequest;
import com.ms.ssw.backend.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/weekly")
    public SchedulePageResponseDTO getWeeklyEmployeeDetails() {
        return scheduleService.getFullSchedulePage();
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

