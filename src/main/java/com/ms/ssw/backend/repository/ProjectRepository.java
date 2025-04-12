package com.ms.ssw.backend.repository;

import com.ms.ssw.backend.model.ProjectLess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectLess, Long> {
    // Можем добавить дополнительные запросы при необходимости
}
