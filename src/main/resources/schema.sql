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

DROP TABLE IF EXISTS project_employee CASCADE;
DROP TABLE IF EXISTS employee_projects CASCADE;
DROP TABLE IF EXISTS project CASCADE;
DROP TABLE IF EXISTS employee CASCADE;

-- Создаем таблицу для хранения информации о текущей неделе
CREATE TABLE IF NOT EXISTS current_week (
                                            id SERIAL PRIMARY KEY,
                                            week_range VARCHAR(50) NOT NULL
);

-- Создаем таблицу для сотрудников (переименовываем в employee)
CREATE TABLE IF NOT EXISTS employee (
                                        id SERIAL PRIMARY KEY,
                                        fio VARCHAR(100) NOT NULL,
                                        week_schedule_id INT UNIQUE,  -- OneToOne
                                        FOREIGN KEY (week_schedule_id) REFERENCES week_schedule(id) ON DELETE SET NULL
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
                                             day_of_week VARCHAR(10) NOT NULL CHECK (
                                                     day_of_week IN ('monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday')
                                                 ),
                                             start_time TIME,
                                             end_time TIME
);

