package com.ms.ssw.backend.repository;

import com.ms.ssw.backend.model.EmployeeLess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeLessRepository extends JpaRepository<EmployeeLess, Long> {
    // Дополнительные запросы
}
