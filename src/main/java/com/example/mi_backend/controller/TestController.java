package com.example.mi_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "¡Mi-BACKEND con Spring Boot 3.5.8 funcionando correctamente!";
    }
    
    @GetMapping("/health")
    public String health() {
        return "OK - Aplicación ejecutándose correctamente";
    }
    
    @GetMapping("/")
    public String home() {
        return "Bienvenido al Backend Mi-BACKEND";
    }
    @GetMapping("")  // Esto manejará la raíz del contexto /api
    public String apiHome() {
         return "Bienvenido al API del Backend Mi-BACKEND";
}
}