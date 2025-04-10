package com.ms.ssw.backend.controller;

import com.ms.ssw.backend.model.LoginDTO;
import com.ms.ssw.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        return authService.authenticate(loginDTO);  // Возвращаем JWT токен
    }
}
