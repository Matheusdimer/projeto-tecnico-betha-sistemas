package com.betha.manutencao.security;

import com.betha.manutencao.domain.enums.TipoFuncionario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class User implements UserDetails {
    private Integer id;

    private String username;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public User() {
    }

    public User(Integer id, String username, String password, TipoFuncionario tipoFuncionario) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(tipoFuncionario.getRole()));
    }

    public Integer getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(TipoFuncionario tipoFuncionario) {
        return this.getAuthorities().contains(new SimpleGrantedAuthority(tipoFuncionario.getRole()));
    }
}
