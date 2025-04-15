package com.ms.ssw.backend.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Можно подключить UserRepository, если база есть
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Пример со статичным пользователем
        if ("admin".equals(username)) {
            return new User("admin", new BCryptPasswordEncoder().encode("1234"), new ArrayList<>());
        }
        throw new UsernameNotFoundException("User not found");
    }
}
