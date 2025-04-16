package com.ms.ssw.backend.repository;

import com.ms.ssw.backend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByName(String name);
    // Можем добавить дополнительные запросы при необходимости
}
