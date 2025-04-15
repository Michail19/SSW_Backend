package com.ms.ssw.backend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenUtil {

//    private final String secretKey = "3j5j3k4ljh3k5ljh3k4ljh3k4ljh3k4ljh3k4ljh3k4ljh3k4ljh3k4ljh3k4lj";  // Используйте безопасный секретный ключ
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // 10 часов
                .signWith(secretKey)  // Используем сгенерированный ключ
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(getUsernameFromToken(token)) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}

curl -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc0NDY5OTI2NCwiZXhwIjoxNzQ0NzM1MjY0fQ.gkqevx1BkXmCbq8HjkwtIHi4VvD0vaQKldUHcBWf0Y1YJ-HxrnpI_cb3TmilDntSq1oNdwugMxveMxBRLLwOJg" http://localhost:8080/api/hello
