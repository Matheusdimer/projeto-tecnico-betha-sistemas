package com.betha.manutencao.services;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {
    public static String authenticated() {
        try {
            return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
