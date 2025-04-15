package com.ms.ssw.backend.controller;

import com.ms.ssw.backend.model.Employee;
import com.ms.ssw.backend.model.EmployeeDetailsResponseDTO;
import com.ms.ssw.backend.model.ProjectDTO;
import com.ms.ssw.backend.model.SchedulePageResponseDTO;
import com.ms.ssw.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/{projectId}")
    public ProjectDTO getProjectById(@PathVariable("projectId") Long projectId) {
        return projectService.getProjectById(projectId);  // Отправляем DTO, полученный из сервиса
    }

    @GetMapping("/all")
    public ProjectDTO getAllProjects() {
        return projectService.getFullProjects();
    }
}
