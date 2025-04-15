package com.ms.ssw.backend.service;

import com.ms.ssw.backend.model.*;
import com.ms.ssw.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.format.TextStyle;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectDTO getProjectById(Long projectId) {
        // Получаем проект из репозитория
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Проект не найден"));

        // Преобразуем сущность Project в ProjectDTO
        List<EmployeeLessDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee : project.getEmployees()) {
            employeeDTOs.add(new EmployeeLessDTO(employee.getId(), employee.getFio()));
        }

        return new ProjectDTO(project.getName(), employeeDTOs);
    }

    public ProjectPageResponseDTO getFullProjects() {
        String currentWeek = formatCurrentWeek(LocalDate.now());
        List<Project> project = projectRepository.findAll();
        Map<String, List<EmployeeLessDTO>> projectsMap = new HashMap<>();

        for (Project project1 : project) {
            List<EmployeeLessDTO> employees = project1.getEmployees().stream()
                    .map(emp -> new EmployeeLessDTO(emp.getId(), emp.getFio()))
                    .collect(Collectors.toList());

            projectsMap.put(project1.getName(), employees);
        }

        return new ProjectPageResponseDTO(currentWeek, projectsMap);
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

    public void changeEmployee(List<ScheduleUpdateRequest> requestList) {

    }
}
