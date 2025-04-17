# Используем официальный образ с JDK 17
FROM gradle:8.5-jdk21 AS builder

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файлы Gradle
COPY build.gradle settings.gradle gradle.properties ./
COPY gradle ./gradle

# Кэшируем зависимости
RUN gradle dependencies --no-daemon || true

# Копируем остальной проект
COPY . .

# Собираем проект
RUN gradle bootJar --no-daemon

# ——— Второй этап: минимальный образ для запуска ———
FROM eclipse-temurin:21-jre-alpine

# Указываем рабочую директорию
WORKDIR /app

# Копируем собранный jar-файл из builder-этапа
COPY --from=builder /app/build/libs/*.jar app.jar

# Открываем порт
EXPOSE 8080

# Команда запуска
ENTRYPOINT ["java", "-jar", "app.jar"]
