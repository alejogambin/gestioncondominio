package com.proyecto.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class usuariosController {
    @GetMapping("/usuarios")
    public String usuarios() {
        return "usuario/Usuarios";
    }
    
}
