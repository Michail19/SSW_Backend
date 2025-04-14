-- -- Создаем таблицу для хранения информации о текущей неделе
-- CREATE TABLE IF NOT EXISTS current_week (
--                                             id SERIAL PRIMARY KEY,
--                                             week_range VARCHAR(50) NOT NULL
--     );
--
-- -- Создаем таблицу для сотрудников
-- CREATE TABLE IF NOT EXISTS employees (
--                                          id SERIAL PRIMARY KEY,
--                                          fio VARCHAR(100) NOT NULL
--     );
--
-- -- Создаем таблицу для проектов
-- CREATE TABLE IF NOT EXISTS projects (
--                                         id SERIAL PRIMARY KEY,
--                                         project_name VARCHAR(100) NOT NULL UNIQUE
--     );
--
-- -- Создаем связующую таблицу для связи сотрудников и проектов (многие-ко-многим)
-- CREATE TABLE IF NOT EXISTS employee_projects (
--                                                  employee_id INT NOT NULL,
--                                                  project_id INT NOT NULL,
--                                                  PRIMARY KEY (employee_id, project_id),
--     FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE,
--     FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE
--     );
--
-- -- Создаем таблицу для расписания сотрудников
-- CREATE TABLE IF NOT EXISTS employee_schedule (
--                                                  week_schedule_id SERIAL PRIMARY KEY,  -- Изменяем id на week_schedule_id
--                                                  employee_id INT NOT NULL,
--                                                  day_of_week VARCHAR(10) NOT NULL,
--     start_time VARCHAR(5),
--     end_time VARCHAR(5),
--     FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE,
--     UNIQUE (employee_id, day_of_week)
--     );
--
--
--

-- Создаем таблицу для хранения информации о текущей неделе
CREATE TABLE IF NOT EXISTS current_week (
                                            id SERIAL PRIMARY KEY,
                                            week_range VARCHAR(50) NOT NULL
    );

-- Создаем таблицу для сотрудников (переименовываем в employee)
CREATE TABLE IF NOT EXISTS employee (
                                        id SERIAL PRIMARY KEY,
                                        fio VARCHAR(100) NOT NULL
    );

-- Создаем таблицу для проектов
CREATE TABLE IF NOT EXISTS project (
                                       id SERIAL PRIMARY KEY,
                                       project_name VARCHAR(100) NOT NULL UNIQUE
    );

-- Создаем связующую таблицу с правильным именем и учетом двунаправленности
CREATE TABLE IF NOT EXISTS employee_projects (
                                                employee_id INT NOT NULL,
                                                project_id INT NOT NULL,
                                                PRIMARY KEY (employee_id, project_id),
    FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE
    );

-- Создаем таблицу для расписания сотрудников с правильными ссылками
CREATE TABLE IF NOT EXISTS week_schedule (
                                             id SERIAL PRIMARY KEY,
                                             employee_id INT NOT NULL UNIQUE,  -- Для OneToOne связи
                                             monday_start VARCHAR(5),
    monday_end VARCHAR(5),
    tuesday_start VARCHAR(5),
    tuesday_end VARCHAR(5),
    wednesday_start VARCHAR(5),
    wednesday_end VARCHAR(5),
    thursday_start VARCHAR(5),
    thursday_end VARCHAR(5),
    friday_start VARCHAR(5),
    friday_end VARCHAR(5),
    saturday_start VARCHAR(5),
    saturday_end VARCHAR(5),
    sunday_start VARCHAR(5),
    sunday_end VARCHAR(5),
    FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE
    );

-- Вставляем данные о текущей неделе
INSERT INTO current_week (week_range) VALUES ('17-23 march 2025');

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
                                                            (1, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_ONO-TEBE-NADO')),
                                                            (1, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_BLOG')),
                                                            (1, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_WEB-LAREK')),
                                                            (1, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_MESTO-PROJECT')),
                                                            (1, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_TRAVEL')),
                                                            (1, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_UCHEBA'));

-- Сотрудник 2 (аналогично первому)
INSERT INTO employee_projects (employee_id, project_id) VALUES
                                                            (2, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_ONO-TEBE-NADO')),
                                                            (2, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_BLOG')),
                                                            (2, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_WEB-LAREK')),
                                                            (2, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_MESTO-PROJECT')),
                                                            (2, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_TRAVEL')),
                                                            (2, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_UCHEBA'));

-- Сотрудник 3 (аналогично первому)
INSERT INTO employee_projects (employee_id, project_id) VALUES
                                                            (3, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_ONO-TEBE-NADO')),
                                                            (3, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_BLOG')),
                                                            (3, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_WEB-LAREK')),
                                                            (3, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_MESTO-PROJECT')),
                                                            (3, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_TRAVEL')),
                                                            (3, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_UCHEBA'));

-- Сотрудник 4
INSERT INTO employee_projects (employee_id, project_id) VALUES
                                                            (4, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_ONO-TEBE-NADO')),
                                                            (4, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_UCHEBA'));

-- Сотрудник 5
INSERT INTO employee_projects (employee_id, project_id) VALUES
                                                            (5, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_MESTO-PROJECT')),
                                                            (5, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_UCHEBA'));

-- Сотрудник 6
INSERT INTO employee_projects (employee_id, project_id) VALUES
                                                            (6, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_ONO-TEBE-NADO')),
                                                            (6, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_BLOG')),
                                                            (6, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_WEB-LAREK'));

-- Сотрудник 7
INSERT INTO employee_projects (employee_id, project_id) VALUES
    (7, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_UCHEBA'));

-- Сотрудник 8
INSERT INTO employee_projects (employee_id, project_id) VALUES
    (8, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_MESTO-PROJECT'));

-- Сотрудник 9
INSERT INTO employee_projects (employee_id, project_id) VALUES
                                                            (9, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_TRAVEL')),
                                                            (9, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_UCHEBA'));

-- Сотрудник 10
INSERT INTO employee_projects (employee_id, project_id) VALUES
                                                            (10, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_ONO-TEBE-NADO')),
                                                            (10, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_BLOG')),
                                                            (10, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_WEB-LAREK'));

-- Сотрудник 11
INSERT INTO employee_projects (employee_id, project_id) VALUES
                                                            (11, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_ONO-TEBE-NADO')),
                                                            (11, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_UCHEBA'));

-- Сотрудник 12
INSERT INTO employee_projects (employee_id, project_id) VALUES
                                                            (12, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_BLOG')),
                                                            (12, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_WEB-LAREK')),
                                                            (12, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_TRAVEL')),
                                                            (12, (SELECT id FROM projects WHERE project_name = 'Project_Yandex_UCHEBA'));

-- Вставляем расписания для сотрудников
-- Сотрудник 1
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (1, 'monday', '08:00', '13:40'),
                                                                                   (1, 'tuesday', '08:00', '15:30'),
                                                                                   (1, 'wednesday', '15:20', '20:00'),
                                                                                   (1, 'thursday', '15:20', '20:00'),
                                                                                   (1, 'friday', '08:00', '15:30'),
                                                                                   (1, 'saturday', NULL, NULL),
                                                                                   (1, 'sunday', NULL, NULL);

-- Сотрудник 2
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (2, 'monday', '08:00', '17:00'),
                                                                                   (2, 'tuesday', '08:00', '17:00'),
                                                                                   (2, 'wednesday', '08:00', '17:00'),
                                                                                   (2, 'thursday', '08:00', '17:00'),
                                                                                   (2, 'friday', '08:00', '16:00'),
                                                                                   (2, 'saturday', '10:00', '14:00'),
                                                                                   (2, 'sunday', '10:00', '14:00');

-- Сотрудник 3
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (3, 'monday', '09:00', '18:00'),
                                                                                   (3, 'tuesday', '09:00', '18:00'),
                                                                                   (3, 'wednesday', '09:00', '18:00'),
                                                                                   (3, 'thursday', '09:00', '18:00'),
                                                                                   (3, 'friday', '09:00', '18:00'),
                                                                                   (3, 'saturday', '10:00', '15:00'),
                                                                                   (3, 'sunday', '10:00', '15:00');

-- Сотрудник 4
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (4, 'monday', '09:00', '18:00'),
                                                                                   (4, 'tuesday', '09:00', '18:00'),
                                                                                   (4, 'wednesday', '09:00', '18:00'),
                                                                                   (4, 'thursday', '09:00', '18:00'),
                                                                                   (4, 'friday', '09:00', '18:00'),
                                                                                   (4, 'saturday', '10:00', '15:00'),
                                                                                   (4, 'sunday', '10:00', '15:00');

-- Сотрудник 5
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (5, 'monday', '09:00', '18:00'),
                                                                                   (5, 'tuesday', '09:00', '18:00'),
                                                                                   (5, 'wednesday', '09:00', '18:00'),
                                                                                   (5, 'thursday', '09:00', '18:00'),
                                                                                   (5, 'friday', '09:00', '18:00'),
                                                                                   (5, 'saturday', '10:00', '15:00'),
                                                                                   (5, 'sunday', '10:00', '15:00');

-- Сотрудник 6
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (6, 'monday', '09:00', '18:00'),
                                                                                   (6, 'tuesday', '09:00', '18:00'),
                                                                                   (6, 'wednesday', '09:00', '18:00'),
                                                                                   (6, 'thursday', '09:00', '18:00'),
                                                                                   (6, 'friday', '09:00', '18:00'),
                                                                                   (6, 'saturday', '10:00', '15:00'),
                                                                                   (6, 'sunday', '10:00', '15:00');

-- Сотрудник 7
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (7, 'monday', '09:00', '18:00'),
                                                                                   (7, 'tuesday', '09:00', '18:00'),
                                                                                   (7, 'wednesday', '09:00', '18:00'),
                                                                                   (7, 'thursday', '09:00', '18:00'),
                                                                                   (7, 'friday', '09:00', '18:00'),
                                                                                   (7, 'saturday', '10:00', '15:00'),
                                                                                   (7, 'sunday', '10:00', '15:00');

-- Сотрудник 8
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (8, 'monday', '09:00', '18:00'),
                                                                                   (8, 'tuesday', '09:00', '18:00'),
                                                                                   (8, 'wednesday', '09:00', '18:00'),
                                                                                   (8, 'thursday', '09:00', '18:00'),
                                                                                   (8, 'friday', '09:00', '18:00'),
                                                                                   (8, 'saturday', '10:00', '15:00'),
                                                                                   (8, 'sunday', '10:00', '15:00');

-- Сотрудник 9
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (9, 'monday', '09:00', '18:00'),
                                                                                   (9, 'tuesday', '09:00', '18:00'),
                                                                                   (9, 'wednesday', '09:00', '18:00'),
                                                                                   (9, 'thursday', '12:00', '18:00'),
                                                                                   (9, 'friday', '09:00', '18:00'),
                                                                                   (9, 'saturday', '10:00', '15:00'),
                                                                                   (9, 'sunday', '10:00', '15:00');

-- Сотрудник 10
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (10, 'monday', '09:00', '18:00'),
                                                                                   (10, 'tuesday', '09:00', '18:00'),
                                                                                   (10, 'wednesday', '09:50', '18:10'),
                                                                                   (10, 'thursday', '09:00', '18:00'),
                                                                                   (10, 'friday', '19:00', '22:00'),
                                                                                   (10, 'saturday', '11:00', '12:00'),
                                                                                   (10, 'sunday', '10:00', '15:00');

-- Сотрудник 11
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (11, 'monday', '09:00', '18:00'),
                                                                                   (11, 'tuesday', '09:10', '18:00'),
                                                                                   (11, 'wednesday', '09:00', '18:00'),
                                                                                   (11, 'thursday', '09:00', '11:00'),
                                                                                   (11, 'friday', '09:30', '18:00'),
                                                                                   (11, 'saturday', '10:00', '15:00'),
                                                                                   (11, 'sunday', '10:00', '15:00');

-- Сотрудник 12
INSERT INTO week_schedule (week_schedule_id, employee_id, day_of_week, start_time, end_time) VALUES
                                                                                   (12, 'monday', '09:00', '18:00'),
                                                                                   (12, 'tuesday', '09:00', '18:00'),
                                                                                   (12, 'wednesday', '09:00', '18:00'),
                                                                                   (12, 'thursday', '09:00', '18:00'),
                                                                                   (12, 'friday', '09:00', '18:00'),
                                                                                   (12, 'saturday', '10:00', '15:00'),
                                                                                   (12, 'sunday', '10:00', '13:00');
