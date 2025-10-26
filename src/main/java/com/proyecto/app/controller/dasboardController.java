package com.proyecto.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class dasboardController {
    @GetMapping("/dasboard")
    public String dasboard(){
        return "dasboard";
    } 
}
