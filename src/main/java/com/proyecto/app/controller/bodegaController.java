package com.proyecto.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class bodegaController {
    @GetMapping("/bodega")
    public String bodega() {
        return "bodega/bodegas";
    }
    
}
