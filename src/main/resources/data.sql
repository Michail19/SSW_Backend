-- Сотрудник 1
INSERT INTO Employees (id, fio, projects) VALUES
    (1, 'Ершов Михаил Алексеевич', 'Project_Yandex_ONO-TEBE-NADO Project_Yandex_BLOG Project_Yandex_WEB-LAREK Project_Yandex_MESTO-PROJECT Project_Yandex_TRAVEL Project_Yandex_UCHEBA');

INSERT INTO WeekSchedule (employee_id, day_of_week, start_time, end_time) VALUES
                                                                              (1, 'monday', '08:00', '13:40'),
                                                                              (1, 'tuesday', '08:00', '15:30'),
                                                                              (1, 'wednesday', '15:20', '20:00'),
                                                                              (1, 'thursday', '15:20', '20:00'),
                                                                              (1, 'friday', '08:00', '15:30'),
                                                                              (1, 'saturday', NULL, NULL),
                                                                              (1, 'sunday', NULL, NULL);

-- Сотрудник 2
INSERT INTO Employees (id, fio, projects) VALUES
    (2, 'Иванов Иван Иванович', 'Project_Yandex_ONO-TEBE-NADO Project_Yandex_BLOG Project_Yandex_WEB-LAREK Project_Yandex_MESTO-PROJECT Project_Yandex_TRAVEL Project_Yandex_UCHEBA');

INSERT INTO WeekSchedule (employee_id, day_of_week, start_time, end_time) VALUES
                                                                              (2, 'monday', '08:00', '17:00'),
                                                                              (2, 'tuesday', '08:00', '17:00'),
                                                                              (2, 'wednesday', '08:00', '17:00'),
                                                                              (2, 'thursday', '08:00', '17:00'),
                                                                              (2, 'friday', '08:00', '16:00'),
                                                                              (2, 'saturday', '10:00', '14:00'),
                                                                              (2, 'sunday', '10:00', '14:00');

-- Сотрудник 3
INSERT INTO Employees (id, fio, projects) VALUES
    (3, 'Петров Петр Петрович', 'Project_Yandex_ONO-TEBE-NADO Project_Yandex_BLOG Project_Yandex_WEB-LAREK Project_Yandex_MESTO-PROJECT Project_Yandex_TRAVEL Project_Yandex_UCHEBA');

INSERT INTO WeekSchedule (employee_id, day_of_week, start_time, end_time) VALUES
                                                                              (3, 'monday', '09:00', '18:00'),
                                                                              (3, 'tuesday', '09:00', '18:00'),
                                                                              (3, 'wednesday', '09:00', '18:00'),
                                                                              (3, 'thursday', '09:00', '18:00'),
                                                                              (3, 'friday', '09:00', '18:00'),
                                                                              (3, 'saturday', '10:00', '15:00'),
                                                                              (3, 'sunday', '10:00', '15:00');
