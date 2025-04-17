package com.ms.ssw.backend.config;

import com.ms.ssw.backend.model.AccessEntity;
import com.ms.ssw.backend.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final User user;
    private final List<AccessEntity> accessList;

    public CustomUserDetails(User user, List<AccessEntity> accessList) {
        this.user = user;
        this.accessList = accessList;
    }

    public Long getId() {
        return user.getId();
    }

    public List<AccessEntity> getAccessList() {
        return accessList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return accessList.stream()
                .map(a -> new SimpleGrantedAuthority(a.getAccessLevel()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // Остальные методы возвращают true
}
