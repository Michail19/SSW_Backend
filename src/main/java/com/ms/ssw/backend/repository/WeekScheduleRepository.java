package com.ms.ssw.backend.repository;

import com.ms.ssw.backend.model.WeekSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WeekScheduleRepository extends JpaRepository<WeekSchedule, Long> {
    Optional<WeekSchedule> findByStartOfWeek(LocalDate date);
}

