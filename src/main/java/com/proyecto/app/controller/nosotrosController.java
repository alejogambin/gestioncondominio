package com.proyecto.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class nosotrosController {
    @GetMapping("/nosotros")
    public String nosotros(){
        return "nosotros";
    }
}
