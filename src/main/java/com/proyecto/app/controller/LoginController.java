package com.proyecto.app.controller;


import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class LoginController {
    /*
    @Autowired
    private UsuarioServiceImpl usuarioService;  */
    @GetMapping("/login")
    public String login() {
        String password = "password1";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt()); 
        System.out.println("password en claro" + password);
        System.out.println("Hashed password: " + hashed);
        return "login";
    }
    /*
    @PostMapping("/ingresar")
    public String ingresar(@ModelAttribute Usuario usuario) {
        List<Usuario> usuarios = usuarioService.findAll();
        for (Usuario u : usuarios) {
            if (usuario.getCorreo()!=null && usuario.getContraseña()!=null && u.getCorreo().equals(usuario.getCorreo()) && u.getContraseña().equals(usuario.getContraseña())) {
                return "redirect:/dasboard";
            }
        }
        return "redirect:/login?error=true";
    }
     */
}
