package com.proyecto.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class estacionamientoController {
    @GetMapping("/estacionamiento")
    public String estacionamiento() {
        return "estacionamiento/estacionamientos";
    }
    
}
