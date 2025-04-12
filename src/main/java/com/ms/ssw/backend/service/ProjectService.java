package com.ms.ssw.backend.service;

import com.ms.ssw.backend.model.ProjectLessDTO;
import com.ms.ssw.backend.model.EmployeeLessDTO;
import com.ms.ssw.backend.model.EmployeeLess;
import com.ms.ssw.backend.model.ProjectLess;
import com.ms.ssw.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectLessDTO getProjectById(Long projectId) {
        // Получаем проект из репозитория
        ProjectLess project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Проект не найден"));

        // Преобразуем сущность Project в ProjectDTO
        List<EmployeeLessDTO> employeeDTOs = new ArrayList<>();
        for (EmployeeLess employee : project.getEmployees()) {
            employeeDTOs.add(new EmployeeLessDTO(employee.getId(), employee.getFio()));
        }

        return new ProjectLessDTO(project.getName(), employeeDTOs);
    }
}
