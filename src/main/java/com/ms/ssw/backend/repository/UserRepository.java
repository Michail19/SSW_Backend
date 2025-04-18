package com.ms.ssw.backend.repository;

import com.ms.ssw.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<Object> findByEmployeeId(Long employeeId);
}
