DROP TABLE IF EXISTS current_week CASCADE;
DROP TABLE IF EXISTS employee_projects CASCADE;
DROP TABLE IF EXISTS project CASCADE;
DROP TABLE IF EXISTS employee CASCADE;
DROP TABLE IF EXISTS day_schedule CASCADE;
DROP TABLE IF EXISTS week_schedule CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- Создаем таблицу для расписания сотрудников с правильными ссылками
CREATE TABLE IF NOT EXISTS day_schedule (
                                            id SERIAL PRIMARY KEY,
                                            "start" VARCHAR(50),
                                            "end" VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS week_schedule (
                                             id SERIAL PRIMARY KEY,
                                             monday_id INT REFERENCES day_schedule(id),
                                             tuesday_id INT REFERENCES day_schedule(id),
                                             wednesday_id INT REFERENCES day_schedule(id),
                                             thursday_id INT REFERENCES day_schedule(id),
                                             friday_id INT REFERENCES day_schedule(id),
                                             saturday_id INT REFERENCES day_schedule(id),
                                             sunday_id INT REFERENCES day_schedule(id),
                                             start_of_week DATE
);

-- Создаем таблицу пользователей
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    level VARCHAR(50) NOT NULL
);

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
                                        user_id INT UNIQUE,  -- связь один к одному с users

                                        FOREIGN KEY (week_schedule_id) REFERENCES week_schedule(id) ON DELETE SET NULL,
                                        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
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
