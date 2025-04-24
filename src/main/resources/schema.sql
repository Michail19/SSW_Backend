DROP TABLE IF EXISTS current_week CASCADE;
DROP TABLE IF EXISTS week_schedule CASCADE;  -- Добавлено
DROP TABLE IF EXISTS employee_projects CASCADE;
DROP TABLE IF EXISTS project CASCADE;
DROP TABLE IF EXISTS employee CASCADE;
DROP TABLE IF EXISTS day_schedule CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- Таблицы для DaySchedule (без изменений)
CREATE TABLE IF NOT EXISTS day_schedule (
                                            id SERIAL PRIMARY KEY,
                                            "start" VARCHAR(50),
                                            "end" VARCHAR(50)
);

-- Users (без изменений)
CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     username VARCHAR(100) NOT NULL UNIQUE,
                                     password VARCHAR(255) NOT NULL,
                                     level VARCHAR(50) NOT NULL
);

-- CurrentWeek (без изменений)
CREATE TABLE IF NOT EXISTS current_week (
                                            id SERIAL PRIMARY KEY,
                                            week_range VARCHAR(50) NOT NULL
);

-- Employee (удален week_schedule_id)
CREATE TABLE IF NOT EXISTS employee (
                                        id SERIAL PRIMARY KEY,
                                        fio VARCHAR(100) NOT NULL,
                                        user_id INT UNIQUE,
                                        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

-- Новая версия WeekSchedule с employee_id
CREATE TABLE IF NOT EXISTS week_schedule (
                                             id SERIAL PRIMARY KEY,
                                             monday_id INT REFERENCES day_schedule(id),
                                             tuesday_id INT REFERENCES day_schedule(id),
                                             wednesday_id INT REFERENCES day_schedule(id),
                                             thursday_id INT REFERENCES day_schedule(id),
                                             friday_id INT REFERENCES day_schedule(id),
                                             saturday_id INT REFERENCES day_schedule(id),
                                             sunday_id INT REFERENCES day_schedule(id),
                                             start_of_week DATE,
                                             employee_id INT NOT NULL,
                                             FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE
);

-- Project (без изменений)
CREATE TABLE IF NOT EXISTS project (
                                       id SERIAL PRIMARY KEY,
                                       project_name VARCHAR(100) NOT NULL UNIQUE
);

-- Employee_Projects (без изменений)
CREATE TABLE IF NOT EXISTS employee_projects (
                                                 employee_id INT NOT NULL,
                                                 project_id INT NOT NULL,
                                                 PRIMARY KEY (employee_id, project_id),
                                                 FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE,
                                                 FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE
);