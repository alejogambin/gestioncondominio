package com.proyecto.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.proyecto.app.entity.Usuario;
import com.proyecto.app.service.impl.UsuarioServiceImpl;




@Controller
public class usuariosController {
    @Autowired
    private UsuarioServiceImpl usuarioService;
    
    @GetMapping("/usuarios")
    public String usuarios() {
        return "usuario/Usuarios";
    }

    @GetMapping("/list")
    public String listUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.findAll();
        model.addAttribute("usuarios", usuarios);
        return "usuario/Usuarios";
    }
    


    @PostMapping("path")
    public String postMethodName(@RequestBody String entity) {
        
        return entity;
    }
    
}
