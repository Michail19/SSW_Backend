package com.ms.ssw.backend.service;

import com.ms.ssw.backend.repository.EmployeeRepository;
import com.ms.ssw.backend.repository.WeekScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private WeekScheduleRepository weekScheduleRepository;


}
