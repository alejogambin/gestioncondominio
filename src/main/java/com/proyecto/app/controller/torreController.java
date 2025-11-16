package com.proyecto.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String listUsuarios(Model model) {
        List<Torre> torre = torreService.findAll();
        model.addAttribute("torres", torre);
        return "torre/list";
    }
    /*  
    @GetMapping("/crear")
    public String crear(Model model) {
        model.addAttribute("usuario", usuarioService.findAll());
        return "usuario/crear";
    }
    

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario) {
        usuarioService.save(usuario);
        return "redirect:/usuario/list";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return "redirect:/usuario/list";
    }
    */
}
