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

import com.proyecto.app.entity.Usuario;
import com.proyecto.app.service.impl.UsuarioServiceImpl;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
@RequestMapping("/usuario")
public class usuariosController {
    @Autowired
    private UsuarioServiceImpl usuarioService;
    
    @GetMapping("/list")
    public String listUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.findAll();
        model.addAttribute("usuarios", usuarios);
        return "usuario/list";
    }
    
    @GetMapping("/json")
    @ResponseBody
    public List<Usuario> listarUsuariosApi(){
        return usuarioService.findAll();
    }
    
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

     @PostMapping("/registrar")
    public String registrar(@ModelAttribute Usuario usuario) {
        usuarioService.save(usuario);
        return "redirect:/login";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return "redirect:/usuario/list";
    }
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model){
        Usuario usuario = usuarioService.findOne(id);
        model.addAttribute("usuario", usuario);
        return "usuario/editar";
    }
    
    
}
