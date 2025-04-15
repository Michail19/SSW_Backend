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

        String projects = details.getProjects().stream()
                .map(Project::getName)
                .collect(Collectors.joining(" "));

        WeekScheduleDTO scheduleDTO = toDto(details.getWeekSchedule());

        return new EmployeeDetailsResponseDTO(
                details.getId(),
                details.getFio(),
                projects,
                scheduleDTO
        );
    }

    private WeekScheduleDTO toDto(WeekSchedule entity) {
        if (entity == null) {
            return createEmptySchedule(); // Возвращаем пустое расписание
        }

        WeekScheduleDTO dto = new WeekScheduleDTO();

        // Для каждого дня проверяем наличие DaySchedule
        dto.setMonday(createDayScheduleDTO(entity.getMonday()));
        dto.setTuesday(createDayScheduleDTO(entity.getTuesday()));
        dto.setWednesday(createDayScheduleDTO(entity.getWednesday()));
        dto.setThursday(createDayScheduleDTO(entity.getThursday()));
        dto.setFriday(createDayScheduleDTO(entity.getFriday()));
        dto.setSaturday(createDayScheduleDTO(entity.getSaturday()));
        dto.setSunday(createDayScheduleDTO(entity.getSunday()));

        return dto;
    }

    private DayScheduleDTO createDayScheduleDTO(DaySchedule daySchedule) {
        if (daySchedule == null) {
            return new DayScheduleDTO(null, null); // Или установите значения по умолчанию
        }
        return new DayScheduleDTO(daySchedule.getStart(), daySchedule.getEnd());
    }

    private WeekScheduleDTO createEmptySchedule() {
        WeekScheduleDTO dto = new WeekScheduleDTO();
        dto.setMonday(new DayScheduleDTO(null, null));
        dto.setTuesday(new DayScheduleDTO(null, null));
        dto.setWednesday(new DayScheduleDTO(null, null));
        dto.setThursday(new DayScheduleDTO(null, null));
        dto.setFriday(new DayScheduleDTO(null, null));
        dto.setSaturday(new DayScheduleDTO(null, null));
        dto.setSunday(new DayScheduleDTO(null, null));
        return dto;
    }
}
