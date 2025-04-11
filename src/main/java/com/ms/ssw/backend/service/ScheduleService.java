package com.ms.ssw.backend.service;

import com.ms.ssw.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Autowired
    private EmployeeRepository employeeRepository;


}
