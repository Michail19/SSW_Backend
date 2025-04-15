package com.ms.ssw.backend.service;

import com.ms.ssw.backend.model.*;
import com.ms.ssw.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
