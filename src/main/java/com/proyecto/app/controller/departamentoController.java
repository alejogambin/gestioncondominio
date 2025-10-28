package com.proyecto.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class departamentoController {
    @GetMapping("/departamento")
    public String departamento() {
        return "departamento/departamentos";
    }
    
}
