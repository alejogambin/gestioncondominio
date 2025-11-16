package com.proyecto.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyecto.app.service.impl.UsuarioServiceImpl;

@Controller
public class dasboardController {
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping("/dasboard")
    public String dasboard(){
        return "dasboard";
    } 
}
