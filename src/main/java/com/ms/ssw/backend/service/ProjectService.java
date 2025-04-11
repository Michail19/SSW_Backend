package com.ms.ssw.backend.service;

import com.ms.ssw.backend.model.ProjectDTO;
import com.ms.ssw.backend.model.EmployeeLessDTO;
import com.ms.ssw.backend.model.EmployeeLess;
import com.ms.ssw.backend.model.Project;
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
        List<EmployeeLessDTO> employeeDTOs = new ArrayList<>();
        for (EmployeeLess employee : project.getEmployees()) {
            employeeDTOs.add(new EmployeeLessDTO(employee.getId(), employee.getFio()));
        }

        return new ProjectDTO(project.getName(), employeeDTOs);
    }
}
