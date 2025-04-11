package com.ms.ssw.backend.repository;

import com.ms.ssw.backend.model.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeDTO, Long> {
    // Дополнительные запросы
}
