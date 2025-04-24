package com.ms.ssw.backend.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class SequenceFixer {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void fixSequences() {
        jdbcTemplate.execute("SELECT setval('week_schedule_id_seq', COALESCE((SELECT MAX(id) FROM week_schedule), 1), true)");
        jdbcTemplate.execute("SELECT setval('day_schedule_id_seq', COALESCE((SELECT MAX(id) FROM day_schedule), 1), true)");
        jdbcTemplate.execute("SELECT setval('users_id_seq', COALESCE((SELECT MAX(id) FROM users), 1), true)");
        jdbcTemplate.execute("SELECT setval('employee_id_seq', COALESCE((SELECT MAX(id) FROM employee), 1), true)");
    }
}

