package com.betha.manutencao.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordens")
public class OrdemController {
    @GetMapping
    public String getOrdens() {
        return "Hello world";
    }
}
