package com.ms.ssw.backend;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptCheck {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

        String rawPassword = "encodedPassword1";
        String hashedPassword = "$2a$12$rtfd779so3luDus8hIQ9KOOO4q8ESh9gbSlXRJvu.tBUpVihLkxua";

        boolean matches = encoder.matches(rawPassword, hashedPassword);
        System.out.println("Пароль совпадает? " + matches); // Должно быть true
    }
}
