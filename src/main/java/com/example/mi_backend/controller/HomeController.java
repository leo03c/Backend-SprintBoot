package com.example.mi_backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "¡Bienvenido a Mi-BACKEND! Endpoints disponibles: /api/, /api/test, /api/health";
    }
}