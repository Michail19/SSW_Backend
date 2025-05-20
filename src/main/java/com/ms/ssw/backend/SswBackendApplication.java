package com.ms.ssw.backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SswBackendApplication {

	public static void main(String[] args) {
		// Если не установлено переменное окружение "RENDER", значит мы в локальной среде
		if (System.getenv("RENDER") == null) {
			// Локальная среда: загружаем переменные из .env
			Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

			setSystemPropertyIfNotNull("DB_URL", dotenv.get("DB_URL"));
			setSystemPropertyIfNotNull("DB_USERNAME", dotenv.get("DB_USERNAME"));
			setSystemPropertyIfNotNull("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
			setSystemPropertyIfNotNull("SPRING_PROFILES_ACTIVE", dotenv.get("SPRING_PROFILES_ACTIVE", "prod"));
		}

		SpringApplication.run(SswBackendApplication.class, args);
	}

	private static void setSystemPropertyIfNotNull(String key, String value) {
		if (value != null) {
			System.setProperty(key, value);
		}
	}
}
