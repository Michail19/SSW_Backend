package com.ms.ssw.backend.repository;

import com.ms.ssw.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Дополнительные запросы
}
