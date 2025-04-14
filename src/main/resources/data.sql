-- Очистка таблиц (добавлена проверка существования)
TRUNCATE TABLE employee CASCADE;
TRUNCATE TABLE project CASCADE;
TRUNCATE TABLE employee_projects CASCADE;
TRUNCATE TABLE week_schedule CASCADE;

-- Вставляем данные о текущей неделе
INSERT INTO current_week (week_range) VALUES ('14-20 april 2025');

-- Вставляем данные сотрудников
INSERT INTO employee (id, fio) VALUES
                                    (1, 'Ершов Михаил Алексеевич'),
                                    (2, 'Иванов Иван Иванович'),
                                    (3, 'Петров Петр Петрович'),
                                    (4, 'Петров Петр Петрович'),
                                    (5, 'Петров Петр Петрович'),
                                    (6, 'Петров Петр Петрович'),
                                    (7, 'Петров Петр Петрович'),
                                    (8, 'Петров Петр Петрович'),
                                    (9, 'Петров Петр Петрович'),
                                    (10, 'Петров Петр Петрович'),
                                    (11, 'Петров Петр Петрович'),
                                    (12, 'Петров Петр Петрович');

-- Вставляем уникальные проекты
INSERT INTO project (project_name) VALUES
                                        ('Project_Yandex_ONO-TEBE-NADO'),
                                        ('Project_Yandex_BLOG'),
                                        ('Project_Yandex_WEB-LAREK'),
                                        ('Project_Yandex_MESTO-PROJECT'),
                                        ('Project_Yandex_TRAVEL'),
                                        ('Project_Yandex_UCHEBA')
    ON CONFLICT (project_name) DO NOTHING;

-- Вставляем связи сотрудников и проектов
-- Сотрудник 1
INSERT INTO employee_projects (employee_id, project_id) VALUES
                                                            (1, (SELECT id FROM project WHERE project_name = 'Project_Yandex_ONO-TEBE-NADO')),
                                                            (1, (SELECT id FROM project WHERE project_name = 'Project_Yandex_BLOG')),
                                                            (1, (SELECT id FROM project WHERE project_name = 'Project_Yandex_WEB-LAREK')),
                                                            (1, (SELECT id FROM project WHERE project_name = 'Project_Yandex_MESTO-PROJECT')),
                                                            (1, (SELECT id FROM project WHERE project_name = 'Project_Yandex_TRAVEL')),
                                                            (1, (SELECT id FROM project WHERE project_name = 'Project_Yandex_UCHEBA'));

-- Сотрудник 2 (аналогично первому)
INSERT INTO employee_projects (employee_id, project_id) VALUES
                                                            (2, (SELECT id FROM project WHERE project_name = 'Project_Yandex_ONO-TEBE-NADO')),
                                                            (2, (SELECT id FROM project WHERE project_name = 'Project_Yandex_BLOG')),
                                                            (2, (SELECT id FROM project WHERE project_name = 'Project_Yandex_WEB-LAREK')),
                                                            (2, (SELECT id FROM project WHERE project_name = 'Project_Yandex_MESTO-PROJECT')),
                                                            (2, (SELECT id FROM project WHERE project_name = 'Project_Yandex_TRAVEL')),
                                                            (2, (SELECT id FROM project WHERE project_name = 'Project_Yandex_UCHEBA'));

-- Сотрудник 3 (аналогично первому)
INSERT INTO employee_projects (employee_id, project_id) VALUES
                                                            (3, (SELECT id FROM project WHERE project_name = 'Project_Yandex_ONO-TEBE-NADO')),
                                                            (3, (SELECT id FROM project WHERE project_name = 'Project_Yandex_BLOG')),
                                                            (3, (SELECT id FROM project WHERE project_name = 'Project_Yandex_WEB-LAREK')),
                                                            (3, (SELECT id FROM project WHERE project_name = 'Project_Yandex_MESTO-PROJECT')),
                                                            (3, (SELECT id FROM project WHERE project_name = 'Project_Yandex_TRAVEL')),
                                                            (3, (SELECT id FROM project WHERE project_name = 'Project_Yandex_UCHEBA'));

-- Сотрудник 4
INSERT INTO employee_projects (employee_id, project_id) VALUES
                                                            (4, (SELECT id FROM project WHERE project_name = 'Project_Yandex_ONO-TEBE-NADO')),
                                                            (4, (SELECT id FROM project WHERE project_name = 'Project_Yandex_UCHEBA'));

-- Сотрудник 5
INSERT INTO employee_projects (employee_id, project_id) VALUES
                                                            (5, (SELECT id FROM project WHERE project_name = 'Project_Yandex_MESTO-PROJECT')),
                                                            (5, (SELECT id FROM project WHERE project_name = 'Project_Yandex_UCHEBA'));

-- Сотрудник 6
INSERT INTO employee_projects (employee_id, project_id) VALUES
                                                            (6, (SELECT id FROM project WHERE project_name = 'Project_Yandex_ONO-TEBE-NADO')),
                                                            (6, (SELECT id FROM project WHERE project_name = 'Project_Yandex_BLOG')),
                                                            (6, (SELECT id FROM project WHERE project_name = 'Project_Yandex_WEB-LAREK'));

-- Сотрудник 7
INSERT INTO employee_projects (employee_id, project_id) VALUES
    (7, (SELECT id FROM project WHERE project_name = 'Project_Yandex_UCHEBA'));

-- Сотрудник 8
INSERT INTO employee_projects (employee_id, project_id) VALUES
    (8, (SELECT id FROM project WHERE project_name = 'Project_Yandex_MESTO-PROJECT'));

-- Сотрудник 9
INSERT INTO employee_projects (employee_id, project_id) VALUES
                                                            (9, (SELECT id FROM project WHERE project_name = 'Project_Yandex_TRAVEL')),
                                                            (9, (SELECT id FROM project WHERE project_name = 'Project_Yandex_UCHEBA'));

-- Сотрудник 10
INSERT INTO employee_projects (employee_id, project_id) VALUES
                                                            (10, (SELECT id FROM project WHERE project_name = 'Project_Yandex_ONO-TEBE-NADO')),
                                                            (10, (SELECT id FROM project WHERE project_name = 'Project_Yandex_BLOG')),
                                                            (10, (SELECT id FROM project WHERE project_name = 'Project_Yandex_WEB-LAREK'));

-- Сотрудник 11
INSERT INTO employee_projects (employee_id, project_id) VALUES
                                                            (11, (SELECT id FROM project WHERE project_name = 'Project_Yandex_ONO-TEBE-NADO')),
                                                            (11, (SELECT id FROM project WHERE project_name = 'Project_Yandex_UCHEBA'));

-- Сотрудник 12
INSERT INTO employee_projects (employee_id, project_id) VALUES
                                                            (12, (SELECT id FROM project WHERE project_name = 'Project_Yandex_BLOG')),
                                                            (12, (SELECT id FROM project WHERE project_name = 'Project_Yandex_WEB-LAREK')),
                                                            (12, (SELECT id FROM project WHERE project_name = 'Project_Yandex_TRAVEL')),
                                                            (12, (SELECT id FROM project WHERE project_name = 'Project_Yandex_UCHEBA'));

-- Вставляем расписания для сотрудников
-- Сотрудник 1
INSERT INTO week_schedule (
    monday_start, monday_end,
    tuesday_start, tuesday_end,
    wednesday_start, wednesday_end,
    thursday_start, thursday_end,
    friday_start, friday_end,
    saturday_start, saturday_end,
    sunday_start, sunday_end
) VALUES (
             '08:00', '13:40',
             '08:00', '15:30',
             '15:20', '20:00',
             '15:20', '20:00',
             '08:00', '15:30',
             NULL, NULL,
             NULL, NULL
         ) RETURNING id;


-- Сотрудник 2
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (1, 2, 'monday', '08:00', '17:00'),
                                                                                   (1, 2, 'tuesday', '08:00', '17:00'),
                                                                                   (1, 2, 'wednesday', '08:00', '17:00'),
                                                                                   (1, 2, 'thursday', '08:00', '17:00'),
                                                                                   (1, 2, 'friday', '08:00', '16:00'),
                                                                                   (1, 2, 'saturday', '10:00', '14:00'),
                                                                                   (1, 2, 'sunday', '10:00', '14:00');

-- Сотрудник 3
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (1, 3, 'monday', '09:00', '18:00'),
                                                                                   (1, 3, 'tuesday', '09:00', '18:00'),
                                                                                   (1, 3, 'wednesday', '09:00', '18:00'),
                                                                                   (1, 3, 'thursday', '09:00', '18:00'),
                                                                                   (1, 3, 'friday', '09:00', '18:00'),
                                                                                   (1, 3, 'saturday', '10:00', '15:00'),
                                                                                   (1, 3, 'sunday', '10:00', '15:00');

-- Сотрудник 4
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (1, 4, 'monday', '09:00', '18:00'),
                                                                                   (1, 4, 'tuesday', '09:00', '18:00'),
                                                                                   (1, 4, 'wednesday', '09:00', '18:00'),
                                                                                   (1, 4, 'thursday', '09:00', '18:00'),
                                                                                   (1, 4, 'friday', '09:00', '18:00'),
                                                                                   (1, 4, 'saturday', '10:00', '15:00'),
                                                                                   (1, 4, 'sunday', '10:00', '15:00');

-- Сотрудник 5
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (1, 5, 'monday', '09:00', '18:00'),
                                                                                   (1, 5, 'tuesday', '09:00', '18:00'),
                                                                                   (1, 5, 'wednesday', '09:00', '18:00'),
                                                                                   (1, 5, 'thursday', '09:00', '18:00'),
                                                                                   (1, 5, 'friday', '09:00', '18:00'),
                                                                                   (1, 5, 'saturday', '10:00', '15:00'),
                                                                                   (1, 5, 'sunday', '10:00', '15:00');

-- Сотрудник 6
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (1, 6, 'monday', '09:00', '18:00'),
                                                                                   (1, 6, 'tuesday', '09:00', '18:00'),
                                                                                   (1, 6, 'wednesday', '09:00', '18:00'),
                                                                                   (1, 6, 'thursday', '09:00', '18:00'),
                                                                                   (1, 6, 'friday', '09:00', '18:00'),
                                                                                   (1, 6, 'saturday', '10:00', '15:00'),
                                                                                   (1, 6, 'sunday', '10:00', '15:00');

-- Сотрудник 7
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (1, 7, 'monday', '09:00', '18:00'),
                                                                                   (1, 7, 'tuesday', '09:00', '18:00'),
                                                                                   (1, 7, 'wednesday', '09:00', '18:00'),
                                                                                   (1, 7, 'thursday', '09:00', '18:00'),
                                                                                   (1, 7, 'friday', '09:00', '18:00'),
                                                                                   (1, 7, 'saturday', '10:00', '15:00'),
                                                                                   (1, 7, 'sunday', '10:00', '15:00');

-- Сотрудник 8
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (1, 8, 'monday', '09:00', '18:00'),
                                                                                   (1, 8, 'tuesday', '09:00', '18:00'),
                                                                                   (1, 8, 'wednesday', '09:00', '18:00'),
                                                                                   (1, 8, 'thursday', '09:00', '18:00'),
                                                                                   (1, 8, 'friday', '09:00', '18:00'),
                                                                                   (1, 8, 'saturday', '10:00', '15:00'),
                                                                                   (1, 8, 'sunday', '10:00', '15:00');

-- Сотрудник 9
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (1, 9, 'monday', '09:00', '18:00'),
                                                                                   (1, 9, 'tuesday', '09:00', '18:00'),
                                                                                   (1, 9, 'wednesday', '09:00', '18:00'),
                                                                                   (1, 9, 'thursday', '12:00', '18:00'),
                                                                                   (1, 9, 'friday', '09:00', '18:00'),
                                                                                   (1, 9, 'saturday', '10:00', '15:00'),
                                                                                   (1, 9, 'sunday', '10:00', '15:00');

-- Сотрудник 10
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (1, 10, 'monday', '09:00', '18:00'),
                                                                                   (1, 10, 'tuesday', '09:00', '18:00'),
                                                                                   (1, 10, 'wednesday', '09:50', '18:10'),
                                                                                   (1, 10, 'thursday', '09:00', '18:00'),
                                                                                   (1, 10, 'friday', '19:00', '22:00'),
                                                                                   (1, 10, 'saturday', '11:00', '12:00'),
                                                                                   (1, 10, 'sunday', '10:00', '15:00');

-- Сотрудник 11
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (1, 11, 'monday', '09:00', '18:00'),
                                                                                   (1, 11, 'tuesday', '09:10', '18:00'),
                                                                                   (1, 11, 'wednesday', '09:00', '18:00'),
                                                                                   (1, 11, 'thursday', '09:00', '11:00'),
                                                                                   (1, 11, 'friday', '09:30', '18:00'),
                                                                                   (1, 11, 'saturday', '10:00', '15:00'),
                                                                                   (1, 11, 'sunday', '10:00', '15:00');

-- Сотрудник 12
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (1, 12, 'monday', '09:00', '18:00'),
                                                                                   (1, 12, 'tuesday', '09:00', '18:00'),
                                                                                   (1, 12, 'wednesday', '09:00', '18:00'),
                                                                                   (1, 12, 'thursday', '09:00', '18:00'),
                                                                                   (1, 12, 'friday', '09:00', '18:00'),
                                                                                   (1, 12, 'saturday', '10:00', '15:00'),
                                                                                   (1, 12, 'sunday', '10:00', '13:00');
