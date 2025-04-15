-- Очистка таблиц (добавлена проверка существования)
TRUNCATE TABLE employee CASCADE;
TRUNCATE TABLE project CASCADE;
TRUNCATE TABLE employee_projects CASCADE;
TRUNCATE TABLE week_schedule CASCADE;
TRUNCATE TABLE current_week CASCADE;

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

-- Добавим расписания для каждого дня
-- Понедельник: 08:00–13:40
INSERT INTO day_schedule ("start", "end") VALUES ('08:00', '13:40') RETURNING id;
-- Допустим, вернулся id = 1

-- Вторник: 08:00–15:30
INSERT INTO day_schedule ("start", "end") VALUES ('08:00', '15:30') RETURNING id;
-- Вернулся id = 2

-- Среда: 15:20–20:00
INSERT INTO day_schedule ("start", "end") VALUES ('15:20', '20:00') RETURNING id;
-- id = 3

-- Четверг: 15:20–20:00
INSERT INTO day_schedule ("start", "end") VALUES ('15:20', '20:00') RETURNING id;
-- id = 4

-- Пятница: 08:00–15:30
INSERT INTO day_schedule ("start", "end") VALUES ('08:00', '15:30') RETURNING id;
-- id = 5

-- Суббота и воскресенье — выходные
INSERT INTO day_schedule ("start", "end") VALUES (NULL, NULL) RETURNING id;
-- id = 6 (суббота)

INSERT INTO day_schedule ("start", "end") VALUES (NULL, NULL) RETURNING id;
-- id = 7 (воскресенье)

-- Теперь собираем неделю
INSERT INTO week_schedule (
    monday_id, tuesday_id, wednesday_id,
    thursday_id, friday_id, saturday_id, sunday_id,
    start_of_week
) VALUES (
             1, 2, 3, 4, 5, 6, 7,
             DATE '2024-04-15'  -- пример понедельника текущей недели
         ) RETURNING id;


-- Сотрудник 2
-- Понедельник-четверг: 08:00–17:00
INSERT INTO day_schedule ("start", "end") VALUES ('08:00', '17:00') RETURNING id; -- id1 (пн)
INSERT INTO day_schedule ("start", "end") VALUES ('08:00', '17:00') RETURNING id; -- id2 (вт)
INSERT INTO day_schedule ("start", "end") VALUES ('08:00', '17:00') RETURNING id; -- id3 (ср)
INSERT INTO day_schedule ("start", "end") VALUES ('08:00', '17:00') RETURNING id; -- id4 (чт)
-- Пятница: 08:00–16:00
INSERT INTO day_schedule ("start", "end") VALUES ('08:00', '16:00') RETURNING id; -- id5 (пт)
-- Суббота-воскресенье: 10:00–14:00
INSERT INTO day_schedule ("start", "end") VALUES ('10:00', '14:00') RETURNING id; -- id6 (сб)
INSERT INTO day_schedule ("start", "end") VALUES ('10:00', '14:00') RETURNING id; -- id7 (вс)

INSERT INTO week_schedule (
    monday_id, tuesday_id, wednesday_id, thursday_id,
    friday_id, saturday_id, sunday_id, start_of_week
) VALUES (8, 9, 10, 11, 12, 13, 14, DATE '2024-04-15');

-- Сотрудник 3-8 (одинаковое расписание)
-- Понедельник-пятница: 09:00–18:00
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '18:00') RETURNING id; -- id8 (пн)
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '18:00') RETURNING id; -- id9 (вт)
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '18:00') RETURNING id; -- id10 (ср)
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '18:00') RETURNING id; -- id11 (чт)
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '18:00') RETURNING id; -- id12 (пт)
-- Суббота-воскресенье: 10:00–15:00
INSERT INTO day_schedule ("start", "end") VALUES ('10:00', '15:00') RETURNING id; -- id13 (сб)
INSERT INTO day_schedule ("start", "end") VALUES ('10:00', '15:00') RETURNING id; -- id14 (вс)

-- Сотрудник 3
INSERT INTO week_schedule (
    monday_id, tuesday_id, wednesday_id, thursday_id,
    friday_id, saturday_id, sunday_id, start_of_week
) VALUES (15, 16, 17, 18, 19, 20, 21, DATE '2024-04-15');
-- Сотрудник 4
INSERT INTO week_schedule (
    monday_id, tuesday_id, wednesday_id, thursday_id,
    friday_id, saturday_id, sunday_id, start_of_week
) VALUES (15, 16, 17, 18, 19, 20, 21, DATE '2024-04-15');
-- Сотрудник 5
INSERT INTO week_schedule (
    monday_id, tuesday_id, wednesday_id, thursday_id,
    friday_id, saturday_id, sunday_id, start_of_week
) VALUES (15, 16, 17, 18, 19, 20, 21, DATE '2024-04-15');
-- Сотрудник 6
INSERT INTO week_schedule (
    monday_id, tuesday_id, wednesday_id, thursday_id,
    friday_id, saturday_id, sunday_id, start_of_week
) VALUES (15, 16, 17, 18, 19, 20, 21, DATE '2024-04-15');
-- Сотрудник 7
INSERT INTO week_schedule (
    monday_id, tuesday_id, wednesday_id, thursday_id,
    friday_id, saturday_id, sunday_id, start_of_week
) VALUES (15, 16, 17, 18, 19, 20, 21, DATE '2024-04-15');
-- Сотрудник 8
INSERT INTO week_schedule (
    monday_id, tuesday_id, wednesday_id, thursday_id,
    friday_id, saturday_id, sunday_id, start_of_week
) VALUES (15, 16, 17, 18, 19, 20, 21, DATE '2024-04-15');

-- Сотрудник 9
-- Понедельник-среда, пятница: 09:00–18:00
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '18:00') RETURNING id; -- id15 (пн)
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '18:00') RETURNING id; -- id16 (вт)
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '18:00') RETURNING id; -- id17 (ср)
-- Четверг: 12:00–18:00
INSERT INTO day_schedule ("start", "end") VALUES ('12:00', '18:00') RETURNING id; -- id18 (чт)
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '18:00') RETURNING id; -- id19 (пт)
-- Суббота-воскресенье: 10:00–15:00
INSERT INTO day_schedule ("start", "end") VALUES ('10:00', '15:00') RETURNING id; -- id20 (сб)
INSERT INTO day_schedule ("start", "end") VALUES ('10:00', '15:00') RETURNING id; -- id21 (вс)

INSERT INTO week_schedule (
    monday_id, tuesday_id, wednesday_id, thursday_id,
    friday_id, saturday_id, sunday_id, start_of_week
) VALUES (22, 23, 24, 25, 26, 27, 28, DATE '2024-04-15');

-- Сотрудник 10
-- Понедельник-вторник: 09:00–18:00
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '18:00') RETURNING id; -- id22 (пн)
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '18:00') RETURNING id; -- id23 (вт)
-- Среда: 09:50–18:10
INSERT INTO day_schedule ("start", "end") VALUES ('09:50', '18:10') RETURNING id; -- id24 (ср)
-- Четверг: 09:00–18:00
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '18:00') RETURNING id; -- id25 (чт)
-- Пятница: 19:00–22:00
INSERT INTO day_schedule ("start", "end") VALUES ('19:00', '22:00') RETURNING id; -- id26 (пт)
-- Суббота: 11:00–12:00
INSERT INTO day_schedule ("start", "end") VALUES ('11:00', '12:00') RETURNING id; -- id27 (сб)
-- Воскресенье: 10:00–15:00
INSERT INTO day_schedule ("start", "end") VALUES ('10:00', '15:00') RETURNING id; -- id28 (вс)

INSERT INTO week_schedule (
    monday_id, tuesday_id, wednesday_id, thursday_id,
    friday_id, saturday_id, sunday_id, start_of_week
) VALUES (29, 30, 31, 32, 33, 34, 35, DATE '2024-04-15');

-- Сотрудник 11
-- Понедельник: 09:00–18:00
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '18:00') RETURNING id; -- id29 (пн)
-- Вторник: 09:10–18:00
INSERT INTO day_schedule ("start", "end") VALUES ('09:10', '18:00') RETURNING id; -- id30 (вт)
-- Среда: 09:00–18:00
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '18:00') RETURNING id; -- id31 (ср)
-- Четверг: 09:00–11:00
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '11:00') RETURNING id; -- id32 (чт)
-- Пятница: 09:30–18:00
INSERT INTO day_schedule ("start", "end") VALUES ('09:30', '18:00') RETURNING id; -- id33 (пт)
-- Суббота-воскресенье: 10:00–15:00
INSERT INTO day_schedule ("start", "end") VALUES ('10:00', '15:00') RETURNING id; -- id34 (сб)
INSERT INTO day_schedule ("start", "end") VALUES ('10:00', '15:00') RETURNING id; -- id35 (вс)

INSERT INTO week_schedule (
    monday_id, tuesday_id, wednesday_id, thursday_id,
    friday_id, saturday_id, sunday_id, start_of_week
) VALUES (36, 37, 38, 39, 40, 41, 42, DATE '2024-04-15');

-- Сотрудник 12
-- Понедельник-пятница: 09:00–18:00
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '18:00') RETURNING id; -- id36 (пн)
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '18:00') RETURNING id; -- id37 (вт)
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '18:00') RETURNING id; -- id38 (ср)
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '18:00') RETURNING id; -- id39 (чт)
INSERT INTO day_schedule ("start", "end") VALUES ('09:00', '18:00') RETURNING id; -- id40 (пт)
-- Суббота: 10:00–15:00
INSERT INTO day_schedule ("start", "end") VALUES ('10:00', '15:00') RETURNING id; -- id41 (сб)
-- Воскресенье: 10:00–13:00
INSERT INTO day_schedule ("start", "end") VALUES ('10:00', '13:00') RETURNING id; -- id42 (вс)

INSERT INTO week_schedule (
    monday_id, tuesday_id, wednesday_id, thursday_id,
    friday_id, saturday_id, sunday_id, start_of_week
) VALUES (43, 44, 45, 46, 47, 48, 49, DATE '2024-04-15');
