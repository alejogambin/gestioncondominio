package com.proyecto.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class usuariosController {
    @GetMapping("/usuarios")
    public String usuarios() {
        return "usuario/Usuarios";
    }

    


    @PostMapping("path")
    public String postMethodName(@RequestBody String entity) {
        
        return entity;
    }
    
}
