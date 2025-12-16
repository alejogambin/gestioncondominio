package com.proyecto.app.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.app.entity.Rol;
import com.proyecto.app.entity.Usuario;
import com.proyecto.app.service.impl.RolServiceImpl;
import com.proyecto.app.service.impl.UsuarioServiceImpl;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/usuario")
public class usuariosController {
    private static final Logger logger = LoggerFactory.getLogger(registroController.class);
    @Autowired
    private UsuarioServiceImpl usuarioService;
    @Autowired
    private RolServiceImpl rolService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/list")
    public String listUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.findAll();
        model.addAttribute("usuarios", usuarios);
        return "usuario/list";
    }

    @GetMapping("/json")
    @ResponseBody
    public List<Usuario> listarUsuariosApi() {
        return usuarioService.findAll();
    }

    @GetMapping("/crear")
    public String crear(Model model) {
        model.addAttribute("roles", rolService.findAll());
        model.addAttribute("usuario", usuarioService.findAll());
        return "usuario/crear";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario, @RequestParam("rolId") Long roleIds) {
        // List<Rol> roles = rolService.findById(roleIds);
        // usuario.setRoles(new HashSet<>(roles));
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
public String editar(@PathVariable Long id, Model model) {
    Usuario usuario = usuarioService.findOne(id);
    model.addAttribute("usuario", usuario);
    model.addAttribute("roles", rolService.findAll());

    return "usuario/editar";
}

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Long id,
            @ModelAttribute Usuario form,
            @RequestParam Long rolId,
            Model model) {
        try {
            Usuario usuario = usuarioService.findOne(id);

            // validar campos obligatorios (del form)
            if (form.getNombre() == null || form.getNombre().isEmpty() ||
                    form.getApellido() == null || form.getApellido().isEmpty() ||
                    form.getEmail() == null || form.getEmail().isEmpty()) {

                model.addAttribute("error", "Todos los campos son obligatorios");
                model.addAttribute("usuario", usuario);
                model.addAttribute("roles", rolService.findAll());
                return "usuario/editar";
            }

            // copiar campos editables
            usuario.setNombre(form.getNombre());
            usuario.setApellido(form.getApellido());
            usuario.setEmail(form.getEmail());

            // password: solo si viene una nueva
            if (form.getPassword() != null && !form.getPassword().isBlank()) {
                usuario.setPassword(passwordEncoder.encode(form.getPassword()));
            }

            // asignar rol (esto llena usuario_roles)
            Rol rol = rolService.findOne(rolId); 
            usuario.setRoles(new HashSet<>(Set.of(rol)));

            usuario.setActivo(true);
            usuarioService.save(usuario);

            return "redirect:/usuario/list"; // o donde corresponda
        } catch (Exception e) {
            logger.error("Error al editar usuario: ", e);
            model.addAttribute("error", "Error al editar: " + e.getMessage());
            model.addAttribute("usuario", form);
            model.addAttribute("roles", rolService.findAll());
            return "usuario/editar";
        }
    }

}
