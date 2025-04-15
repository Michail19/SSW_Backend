package com.ms.ssw.backend.service;

import com.ms.ssw.backend.model.*;
import com.ms.ssw.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectDTO getProjectById(Long projectId) {
        // Получаем проект из репозитория
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Проект не найден"));

        // Преобразуем сущность Project в ProjectDTO
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee : project.getEmployees()) {
            employeeDTOs.add(new EmployeeDTO(employee.getId(), employee.getFio()));
        }

        return new ProjectDTO(project.getName(), employeeDTOs);
    }

    public ProjectDTO getFullProjects() {
        List<Project> project = projectRepository.findAll();

        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Project project1 : project) {
            for (Employee employee : project1.getEmployees()) {
                employeeDTOs.add(new EmployeeDTO(employee.getId(), employee.getFio()));
            }
        }

        String currentWeek = formatCurrentWeek(LocalDate.now());

        return new ProjectPageResponseDTO(currentWeek, employeeDTOs);
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
}
