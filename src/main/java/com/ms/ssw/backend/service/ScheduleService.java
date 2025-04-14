package com.ms.ssw.backend.service;

import com.ms.ssw.backend.model.*;
import com.ms.ssw.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    @Autowired
    private EmployeeRepository employeeDetailsRepository;

    public SchedulePageResponseDTO getFullSchedulePage() {
        List<Employee> detailsList = employeeDetailsRepository.findAll();

        List<EmployeeDetailsResponseDTO> employeeDTOs = detailsList.stream()
                .map(this::convertToDTO)
                .toList();

        String currentWeek = formatCurrentWeek(LocalDate.now());

        return new SchedulePageResponseDTO(currentWeek, employeeDTOs);
    }

    private String formatCurrentWeek(LocalDate today) {
        LocalDate monday = today.with(DayOfWeek.MONDAY);
        LocalDate sunday = today.with(DayOfWeek.SUNDAY);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
        String startDay = monday.format(formatter);
        String endDay = sunday.format(formatter);

        String month = monday.getMonth().getDisplayName(TextStyle.FULL, new Locale("en")).toLowerCase();
        int year = monday.getYear();

        return String.format("%s-%s %s %d", startDay, endDay, month, year);
    }

    private EmployeeDetailsResponseDTO convertToDTO(Employee details) {
        EmployeeLess employee = details.getEmployeeData();

        String projects = employee.getProjects().stream()
                .map(Project::getName)
                .collect(Collectors.joining(" "));

        WeekScheduleDTO scheduleDTO = toDto(details.getWeekSchedule());

        return new EmployeeDetailsResponseDTO(
                details.getId(),
                employee.getFio(),
                projects,
                scheduleDTO
        );
    }

    private WeekScheduleDTO toDto(WeekSchedule entity) {
        WeekScheduleDTO dto = new WeekScheduleDTO();

        dto.setMonday(new DayScheduleDTO(entity.getMonday().getStart(), entity.getMonday().getEnd()));
        dto.setTuesday(new DayScheduleDTO(entity.getTuesday().getStart(), entity.getTuesday().getEnd()));
        dto.setWednesday(new DayScheduleDTO(entity.getWednesday().getStart(), entity.getWednesday().getEnd()));
        dto.setThursday(new DayScheduleDTO(entity.getThursday().getStart(), entity.getThursday().getEnd()));
        dto.setFriday(new DayScheduleDTO(entity.getFriday().getStart(), entity.getFriday().getEnd()));
        dto.setSaturday(new DayScheduleDTO(entity.getSaturday().getStart(), entity.getSaturday().getEnd()));
        dto.setSunday(new DayScheduleDTO(entity.getSunday().getStart(), entity.getSunday().getEnd()));

        return dto;
    }
}
