package com.proyecto.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.proyecto.app.entity.Usuario;
import com.proyecto.app.service.impl.UsuarioServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class registroController {
    private static final Logger logger = LoggerFactory.getLogger(registroController.class);
    
    @Autowired
    private UsuarioServiceImpl usuarioService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/registro")
    public String registro(){
        return "registro";
    }
    
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        try {
            // Validar que los campos obligatorios no estén vacíos
            if (usuario.getNombre() == null || usuario.getNombre().isEmpty() ||
                usuario.getApellido() == null || usuario.getApellido().isEmpty() ||
                usuario.getEmail() == null || usuario.getEmail().isEmpty() ||
                usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
                model.addAttribute("error", "Todos los campos son obligatorios");
                return "registro";
            }
            
            // Encriptar la contraseña antes de guardar
            String passwordEncriptada = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(passwordEncriptada);
            usuario.setActivo(true);
            usuarioService.save(usuario);
            return "redirect:/login?success=true";
        } catch (Exception e) {
            logger.error("Error al registrar usuario: ", e);
            model.addAttribute("error", "Error al registrar: " + e.getMessage());
            return "registro";
        }
    }
}
