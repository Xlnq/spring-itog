package com.example.skillfactory.config;

import com.example.skillfactory.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Возвращаем роль как SimpleGrantedAuthority, который реализует GrantedAuthority
        return user.getRole() != null
                ? Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleName())) // Префикс ROLE_
                : Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Аккаунт не истекает
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Аккаунт не заблокирован
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Пароль не истекает
    }

    @Override
    public boolean isEnabled() {
        return true;  // Пользователь активен
    }

    public User getUser() {
        return user;
    }
}
