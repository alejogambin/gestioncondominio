package com.proyecto.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.app.entity.Torre;
import com.proyecto.app.service.impl.TorreServiceImpl;

@Controller
@RequestMapping("/torre")
public class torreController {
    @Autowired
    private TorreServiceImpl torreService;
    
    @GetMapping("/torre")
    public String Torre(){
        return "torre/list";
    }
    @GetMapping("/list")
    public String listTorres(Model model) {
        List<Torre> torres = torreService.findAll();
        model.addAttribute("torres", torres);
        return "torre/list";
    }
      
    @GetMapping("/crear")
    public String crear(Model model) {
        model.addAttribute("torre", torreService.findAll());
        return "torre/crear";
    }
    

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Torre torre) {
        torreService.save(torre);
        return "redirect:/torre/list";
    } 
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        torreService.delete(id);
        return "redirect:/torre/list";
    }
}
