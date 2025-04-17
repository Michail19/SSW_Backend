# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.4/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.4/gradle-plugin/packaging-oci-image.html)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

-----------------------

**controller**
* Контроллеры обрабатывают HTTP-запросы (@RestController, @Controller)
* Делегируют обработку в service
* Отдают JSON, HTML или другие ответы

**service**
* Содержит бизнес-логику приложения
* Использует @Service
* Работает с репозиториями и формирует результат

**repository**
* Интерфейсы с @Repository или extends JpaRepository, CrudRepository
* Доступ к БД
* Автоматически реализуются Spring Data JPA

**model или entity**
* Сущности (@Entity) — мапятся на таблицы БД
* DTO (Data Transfer Object) — для передачи данных между слоями
* Классы запросов (LoginRequest, UserDto и т.п.)

**config**
* Конфигурационные классы:
* * @Configuration
* * @EnableWebSecurity
* * @EnableSwagger2, и т.п.
* Здесь обычно WebSecurityConfigurerAdapter, CORS, Swagger и пр. 

**exception**
* Кастомные исключения
* Глобальные обработчики ошибок (@ControllerAdvice + @ExceptionHandler)
* Кастомизация ответов при ошибках

----------------------------

Запуск ДБ локально:
```
docker run --name my-postgres -e POSTGRES_USER=myuser -e POSTGRES_PASSWORD=mypassword -e POSTGRES_DB=mydb -p 5432:5432 -d postgres:15
```

Пингануть:
```
http://localhost:8080/api/ping
```

Пингануть БД с проектами:
```
http://localhost:8080/api/users
```
```
curl http://localhost:8080/api/employees/weekly
```

----------------

Давайте разберемся, как работают различные методы в примере с контроллером, сервисом и репозиторием. Я поясню работу каждого метода, исключая геттеры и сеттеры.

### 1. **Контроллер (Controller)**

**Контроллер** обрабатывает HTTP-запросы от клиентов и передает данные в сервис для дальнейшей обработки. Контроллер может выполнять проверку прав доступа, а также обрабатывать и отправлять ответы в формате, понятном клиенту (например, DTO).

```java
@GetMapping("/{projectId}")
public ProjectDTO getProjectById(@PathVariable("projectId") Long projectId) {
    return projectService.getProjectById(projectId);  // Отправляем DTO, полученный из сервиса
}
```

#### Объяснение:
- **@GetMapping("/{projectId}")**: Этот метод будет обрабатывать HTTP GET запросы на адрес `/projects/{projectId}`. Значение `{projectId}` будет извлечено из URL и передано в метод как параметр `projectId`.
- **@PathVariable("projectId")**: Аннотация `@PathVariable` указывает, что значение из URL будет привязано к параметру метода `projectId`.
- **projectService.getProjectById(projectId)**: Контроллер вызывает метод сервиса `projectService.getProjectById()` и передает в него `projectId`. Сервис возвращает `ProjectDTO`, который затем отправляется в ответе.

### 2. **Сервис (Service)**

**Сервис** — это слой бизнес-логики. Сервис обращается к репозиториям для получения данных из базы данных и преобразует их в удобный формат для контроллеров (например, в DTO).

```java
public ProjectDTO getProjectById(Long projectId) {
    // Получаем проект из репозитория
    Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Проект не найден"));

    // Преобразуем сущность Project в ProjectDTO
    List<EmployeeDTO> employeeDTOs = new ArrayList<>();
    for (Employee employee : project.getEmployees()) {
        employeeDTOs.add(new EmployeeDTO(employee.getId(), employee.getFio()));
    }

    return new ProjectDTO(project.getName(), employeeDTOs);
}
```

#### Объяснение:
- **projectRepository.findById(projectId)**: В сервисе вызывается метод репозитория для получения проекта по его идентификатору (`projectId`). Если проект не найден, выбрасывается исключение с сообщением "Проект не найден".
- **new EmployeeDTO(employee.getId(), employee.getFio())**: Для каждого сотрудника проекта создается объект `EmployeeDTO`, который содержит только необходимые данные (например, `id` и `fio`), а не всю сущность `Employee`. Это позволяет избежать излишней нагрузки и передать только нужные данные.
- **new ProjectDTO(project.getName(), employeeDTOs)**: Создается объект `ProjectDTO`, который содержит название проекта и список сотрудников в формате `EmployeeDTO`. Этот объект возвращается контроллеру.

### 3. **Репозиторий (Repository)**

**Репозиторий** используется для взаимодействия с базой данных. Spring Data JPA предоставляет базовые операции для работы с базой данных (например, создание, чтение, обновление и удаление записей).

```java
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Можем добавить дополнительные запросы при необходимости
}
```

#### Объяснение:
- **ProjectRepository extends JpaRepository<Project, Long>**: Репозиторий использует интерфейс `JpaRepository`, который автоматически предоставляет базовые методы для работы с сущностями `Project`, такими как `findById()`, `save()`, `delete()`, и другие. Мы можем добавить дополнительные запросы в репозиторий, если это необходимо.

### 4. **Сущности (Entities)**

Сущности — это классы, которые представляют данные, хранящиеся в базе данных. Эти классы используются в репозиториях для взаимодействия с базой данных через JPA (Java Persistence API).

#### Пример для `Project`:
```java
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    @JoinColumn(name = "project_id")
    private List<Employee> employees;

    // Конструкторы, геттеры и сеттеры
}
```

#### Объяснение:
- **@Entity**: Эта аннотация указывает, что класс `Project` является сущностью, которая будет отображаться в таблицу базы данных.
- **@Id**: Указывает, что поле `id` является уникальным идентификатором для этой сущности.
- **@GeneratedValue(strategy = GenerationType.IDENTITY)**: Генерация значений для поля `id` будет происходить автоматически, используя стратегию `IDENTITY`, что означает автоинкремент в базе данных.
- **@OneToMany**: Указывает, что у проекта может быть несколько сотрудников. Связь один ко многим с сущностью `Employee`.
- **@JoinColumn(name = "project_id")**: Указывает, что колонка `project_id` будет использоваться для связи с таблицей сотрудников.

#### Пример для `Employee`:
```java
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fio;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    // Конструкторы, геттеры и сеттеры
}
```

#### Объяснение:
- **@ManyToOne**: Указывает, что каждый сотрудник связан с одним проектом. Это связь многие к одному.
- **@JoinColumn(name = "project_id")**: Указывает, что колонка `project_id` будет использоваться для хранения ссылки на проект, к которому принадлежит сотрудник.

---

### Резюме:
- **Контроллер** обрабатывает HTTP-запросы, извлекает параметры из URL, вызывает сервис для обработки данных и отправляет ответы клиенту.
- **Сервис** содержит бизнес-логику, получает данные из репозитория, преобразует их в DTO и передает контроллеру.
- **Репозиторий** взаимодействует с базой данных, используя JPA, и выполняет запросы для извлечения, сохранения или удаления данных.
- **Сущности** описывают структуру таблиц базы данных, включая их связи.

Таким образом, каждый слой (контроллер, сервис, репозиторий) играет свою роль, обеспечивая разделение ответственности и упрощая поддержку и расширение приложения.

Если сотрудник связан с проектами как **многие ко многим** (то есть один сотрудник может быть связан с несколькими проектами, и каждый проект может содержать несколько сотрудников), то в модели данных необходимо будет изменить связь между `Employee` и `Project`.

### Обновление модели с отношением "многие ко многим"

Для реализации связи **многие ко многим** в JPA (Java Persistence API) можно использовать аннотацию `@ManyToMany`. Вам также потребуется создать таблицу связи (или таблицу маппинга), которая будет хранить связи между сотрудниками и проектами. JPA автоматически создаст эту таблицу, если вы используете аннотацию `@ManyToMany`.

### Изменение сущностей

#### Сущность `Employee` (Сотрудник)

```java
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fio;

    @ManyToMany(mappedBy = "employees")
    private List<Project> projects = new ArrayList<>();

    // Конструкторы, геттеры и сеттеры
}
```

#### Объяснение:
- **@ManyToMany**: Эта аннотация указывает, что у сотрудника может быть несколько проектов, с которыми он связан. `mappedBy` указывает на поле в сущности `Project`, которое владеет этой связью.
- **projects**: Список проектов, к которым сотрудник может быть привязан.

#### Сущность `Project` (Проект)

```java
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
        name = "project_employee", // Название таблицы связи
        joinColumns = @JoinColumn(name = "project_id"), // Колонка для связи с проектом
        inverseJoinColumns = @JoinColumn(name = "employee_id") // Колонка для связи с сотрудником
    )
    private List<Employee> employees = new ArrayList<>();

    // Конструкторы, геттеры и сеттеры
}
```

#### Объяснение:
- **@ManyToMany**: Указывает, что у проекта может быть несколько сотрудников. Это отношение **многие ко многим**.
- **@JoinTable**: Определяет таблицу связи между проектами и сотрудниками. В данном случае таблица будет называться `project_employee`, и она будет содержать два столбца: `project_id` и `employee_id`, которые ссылаются на соответствующие сущности.
- **joinColumns** и **inverseJoinColumns**: Эти аннотации указывают, какие столбцы будут использоваться для связывания двух таблиц (проектов и сотрудников).

### Пример работы с данными

После того как связь многие ко многим настроена, вы можете добавлять сотрудников к проектам и наоборот.

#### Добавление сотрудников в проект

```java
public void addEmployeeToProject(Long projectId, Long employeeId) {
    Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Проект не найден"));
    Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Сотрудник не найден"));

    project.getEmployees().add(employee);  // Добавляем сотрудника в проект
    employee.getProjects().add(project);   // Добавляем проект сотруднику

    projectRepository.save(project);
    employeeRepository.save(employee);
}
```

#### Объяснение:
- В этом примере добавляется сотрудник в проект. Мы находим проект и сотрудника по их идентификаторам и добавляем их в соответствующие списки: сотрудника в проект и проект в сотрудника.
- После изменения коллекций `employees` в `Project` и `projects` в `Employee` сохраняем их с помощью репозиториев `projectRepository.save(project)` и `employeeRepository.save(employee)`.

### Репозиторий

```java
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Дополнительные запросы
}

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Дополнительные запросы
}
```

- Репозитории могут оставаться такими же, как и раньше, потому что `@ManyToMany` и `@JoinTable` управляют связью между сущностями на уровне базы данных.

### Преимущества

1. **Гибкость**: Теперь вы можете легко управлять отношениями между сотрудниками и проектами, добавляя и удаляя связи без изменения структуры таблиц в базе данных.
2. **Отсутствие дублирования**: В отличие от связи "один ко многим", где каждый сотрудник привязан к одному проекту, "многие ко многим" позволяет сотрудникам быть частью нескольких проектов, а проекты могут содержать несколько сотрудников.
3. **Автоматическое создание таблицы связи**: JPA автоматически создаст таблицу `project_employee`, которая будет использоваться для хранения связей между проектами и сотрудниками.

---

### Заключение

В случае с отношением **многие ко многим**, необходимо использовать аннотацию `@ManyToMany`, которая указывает на связь между сущностями. В JPA, при правильной настройке таблицы связи через `@JoinTable`, эта связь будет эффективно поддерживаться. Важно отметить, что с таким подходом вы можете легко добавлять или удалять связи между проектами и сотрудниками, а таблица связи будет автоматически управляться через репозитории.

-------------------

POST JSON's

Для расписания:
```json
[
  {
    "employeeId": 1,
    "weekStart": "2024-04-15",
    "schedule": {
      "monday":    { "start": "03:00", "end": "13:42" },
      "tuesday":   { "start": "08:10", "end": "15:30" },
      "wednesday": { "start": "15:20", "end": "20:20" },
      "thursday":  { "start": "15:30", "end": "21:30" },
      "friday":    { "start": "08:02", "end": "15:20" },
      "saturday":  { "start": null,    "end": null    },
      "sunday":    { "start": "08:00", "end": "12:30" }
    }
  },
  {
    "employeeId": 2,
    "weekStart": "2024-04-15",
    "schedule": {
      "monday":    { "start": "08:02", "end": "17:00" }
    }
  }
]
```

```bash
curl -X POST http://localhost:8080/projects/change \
  -H "Authorization: Bearer <ВАШ_ТОКЕН>" \
  -H "Content-Type: application/json" \
  -d '[
    {
      "action": "add",
      "fio": [
        { "id": 2, "fio": "Иванов Иван Иванович" },
        { "id": 5, "fio": "Петров Петр Петрович" },
        { "id": 6, "fio": "Петров Петр Петрович" }
      ],
      "project": "Project_Yandex_WEB-LAREK"
    }
  ]'

curl -X POST http://localhost:8080/projects/change \
  -H "Authorization: Bearer <ВАШ_ТОКЕН>" \
  -H "Content-Type: application/json" \
  -d '[
    {
      "action": "remove",
      "fio": [
        { "id": 2, "fio": "Иванов Иван Иванович" }
      ],
      "project": "Project_Yandex_WEB-LAREK"
    }
  ]'

curl -X POST http://localhost:8080/projects/change \
  -H "Authorization: Bearer <ВАШ_ТОКЕН>" \
  -H "Content-Type: application/json" \
  -d '[
    {
      "action": "add",
      "fio": [
        { "id": 7, "fio": "Петров Петр Петрович" },
        { "id": 8, "fio": "Петров Петр Петрович" },
        { "id": 9, "fio": "Петров Петр Петрович" },
        { "id": 10, "fio": "Петров Петр Петрович" }
      ],
      "project": "Project_Yandex_BLOG"
    }
  ]'

curl -X POST http://localhost:8080/projects/change \
  -H "Authorization: Bearer <ВАШ_ТОКЕН>" \
  -H "Content-Type: application/json" \
  -d '[
    {
      "action": "remove",
      "fio": [
        { "id": 9, "fio": "Петров Петр Петрович" },
        { "id": 10, "fio": "Петров Петр Петрович" },
        { "id": 11, "fio": "Петров Петр Петрович" },
        { "id": 12, "fio": "Петров Петр Петрович" }
      ],
      "project": "Project_Yandex_BLOG"
    }
  ]'

curl -X POST http://localhost:8080/projects/change \
  -H "Authorization: Bearer <ВАШ_ТОКЕН>" \
  -H "Content-Type: application/json" \
  -d '[
    {
      "action": "remove",
      "fio": [
        { "id": 9, "fio": "Петров Петр Петрович" },
        { "id": 10, "fio": "Петров Петр Петрович" },
        { "id": 11, "fio": "Петров Петр Петрович" },
        { "id": 12, "fio": "Петров Петр Петрович" }
      ],
      "project": "Project_Yandex_BLOG"
    }
  ]'

```

-------------------

Запуск:
```bash
curl -X POST http://localhost:8080/auth/login -H "Content-Type: application/json" -d "{\"username\":\"ershov.m\",\"password\":\"encodedPassword1\"}"
```

```bash
curl -H "Authorization: Bearer /./" http://localhost:8080/schedule/weekly
```
```bash
curl -H "Authorization: Bearer /./" http://localhost:8080/schedule/weekly?date=2024-04-21
```

```bash
curl -X POST http://localhost:8080/schedule/update -H "Content-Type: application/json" -H "Authorization: Bearer /./" -d "[{\"employeeId\": 1, \"weekStart\": \"2024-04-14\", \"schedule\": {\"monday\": {\"start\": \"03:00\", \"end\": \"13:42\"}, \"tuesday\": {\"start\": \"08:10\", \"end\": \"15:30\"}, \"wednesday\": {\"start\": \"15:20\", \"end\": \"20:20\"}, \"thursday\": {\"start\": \"15:30\", \"end\": \"21:30\"}, \"friday\": {\"start\": \"08:02\", \"end\": \"15:20\"}, \"saturday\": {\"start\": null, \"end\": null}, \"sunday\": {\"start\": \"08:00\", \"end\": \"12:30\"}}}, {\"employeeId\": 2, \"weekStart\": \"2024-04-14\", \"schedule\": {\"monday\": {\"start\": \"08:02\", \"end\": \"17:00\"}}}]"
```
```bash
curl -X POST http://localhost:8080/schedule/update -H "Content-Type: application/json" -H "Authorization: Bearer /./" -d "[{\"employeeId\": 1, \"weekStart\": \"2024-04-21\", \"schedule\": {\"monday\": {\"start\": \"03:00\", \"end\": \"13:42\"}, \"tuesday\": {\"start\": \"08:10\", \"end\": \"15:30\"}, \"wednesday\": {\"start\": \"15:20\", \"end\": \"20:20\"}, \"thursday\": {\"start\": \"15:30\", \"end\": \"21:30\"}, \"friday\": {\"start\": \"08:02\", \"end\": \"15:20\"}, \"saturday\": {\"start\": null, \"end\": null}, \"sunday\": {\"start\": \"08:00\", \"end\": \"12:30\"}}}, {\"employeeId\": 2, \"weekStart\": \"2024-04-21\", \"schedule\": {\"monday\": {\"start\": \"08:02\", \"end\": \"17:00\"}}}]"
```

```bash
curl -X POST http://localhost:8080/schedule/add -H "Content-Type: application/json" -H "Authorization: Bearer /./" -d "{\"fio\":\"Иванов Иван Иоанович\", \"schedule\": {\"monday\": {\"start\": \"03:00\", \"end\": \"13:42\"}, \"tuesday\": {\"start\": \"08:10\", \"end\": \"15:30\"}, \"wednesday\": {\"start\": \"15:20\", \"end\": \"20:20\"}, \"thursday\": {\"start\": \"15:30\", \"end\": \"21:30\"}, \"friday\": {\"start\": \"08:02\", \"end\": \"15:20\"}, \"saturday\": {\"start\": null, \"end\": null}, \"sunday\": {\"start\": \"08:00\", \"end\": \"12:30\"}}}"
```

```bash
curl -X DELETE http://localhost:8080/schedule/delete/5 -H "Authorization: Bearer /./"
```

```bash
curl -X POST http://localhost:8080/projects/change -H "Authorization: Bearer /./" -H "Content-Type: application/json" -d "[{ \"action\": \"add\", \"fio\": [ { \"id\": 2, \"fio\": \"Иванов Иван Иванович\" }, { \"id\": 5, \"fio\": \"Петров Петр Петрович\" }, { \"id\": 6, \"fio\": \"Петров Петр Петрович\" } ], \"project\": \"Project_Yandex_WEB-LAREK\" }, { \"action\": \"remove\", \"fio\": [ { \"id\": 2, \"fio\": \"Иванов Иван Иванович\" } ], \"project\": \"Project_Yandex_WEB-LAREK\" }, { \"action\": \"add\", \"fio\": [ { \"id\": 7, \"fio\": \"Петров Петр Петрович\" }, { \"id\": 8, \"fio\": \"Петров Петр Петрович\" }, { \"id\": 9, \"fio\": \"Петров Петр Петрович\" }, { \"id\": 10, \"fio\": \"Петров Петр Петрович\" } ], \"project\": \"Project_Yandex_BLOG\" }, { \"action\": \"remove\", \"fio\": [ { \"id\": 9, \"fio\": \"Петров Петр Петрович\" }, { \"id\": 10, \"fio\": \"Петров Петр Петрович\" }, { \"id\": 11, \"fio\": \"Петров Петр Петрович\" }, { \"id\": 12, \"fio\": \"Петров Петр Петрович\" } ], \"project\": \"Project_Yandex_BLOG\" }]"
```

```sql
SELECT e.id AS employee_id, e.fio, u.username, u.level AS role FROM employee e JOIN users u ON e.user_id = u.id;
```
