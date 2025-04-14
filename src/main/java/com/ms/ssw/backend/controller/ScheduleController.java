package com.ms.ssw.backend.controller;

import com.ms.ssw.backend.model.SchedulePageResponseDTO;
import com.ms.ssw.backend.service.ScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

