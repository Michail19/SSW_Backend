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
