package com.ms.ssw.backend.controller;

import com.ms.ssw.backend.model.*;
import com.ms.ssw.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ProjectPageResponseDTO getAllProjects() {
        return projectService.getFullProjects();
    }

    @PostMapping("/change")
    public ResponseEntity<?> changeProjectEmployee(@RequestBody List<EmployeeProjectsDTO> requestList) {
        projectService.changeEmployee(requestList);
        return ResponseEntity.ok().build();
    }
}
