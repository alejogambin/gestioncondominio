package com.proyecto.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.app.entity.Usuario;
import com.proyecto.app.service.impl.UsuarioServiceImpl;

@Controller
public class LoginController {
    @Autowired
    private UsuarioServiceImpl usuarioService; 
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/ingresar")
    public String ingresar(@ModelAttribute Usuario usuario) {
        List<Usuario> usuarios = usuarioService.findAll();
        for (Usuario u : usuarios) {
            if (u.getCorreo().equals(usuario.getCorreo()) && u.getContraseña().equals(usuario.getContraseña())) {
                return "redirect:/dasboard";
            }
        }
        return "redirect:/login?error=true";
    }

}
