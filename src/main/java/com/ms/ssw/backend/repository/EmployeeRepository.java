package com.ms.ssw.backend.repository;

import com.ms.ssw.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findById(Long employeeId);
    // Дополнительные запросы
}
