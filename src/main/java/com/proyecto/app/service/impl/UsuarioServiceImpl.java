package com.proyecto.app.service.impl;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.proyecto.app.DAO.IUsuarioDao;
import com.proyecto.app.entity.Usuario;
import com.proyecto.app.service.UsuarioService;

import org.springframework.transaction.annotation.Transactional;
@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {
    @Autowired
    private IUsuarioDao usuarioDao;
    
    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioDao.save(usuario);
    }
    @Transactional
    public void delete(Long id_user) {
        usuarioDao.deleteById(id_user);
    }
    @Transactional
    public Usuario findOne(Long id_user) {
        return usuarioDao.findById(id_user).orElse(null);
    }
    @Transactional
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDao.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Usuario usuario = usuarioDao.findByEmail(email).orElseThrow(()->
            new UsernameNotFoundException("Usuario no encontrado con email: "+email));
        if (!usuario.isActivo()){
            throw new UsernameNotFoundException("La cuenta de "+ email+ "esta desactivada. contacte al administrador.");
        }
        
        Set<GrantedAuthority> authorities = new HashSet<>();
        
        // Si el usuario tiene roles, usarlos
        if (usuario.getRoles() != null && !usuario.getRoles().isEmpty()) {
            authorities = usuario.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .collect(Collectors.toSet());
        } else {
            // Si no tiene roles, asignarle "USER" por defecto
            authorities.add(new SimpleGrantedAuthority("USER"));
        }

        return User.builder()
            .username(usuario.getEmail())
            .password(usuario.getPassword())
            .authorities(authorities)
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(!usuario.isActivo())
            .build();           
    }
    @Override
    public List<Usuario> ListAllUsuario() {
        List<Usuario> usuarios = usuarioDao.ListAllUsuario();
        return usuarios.stream()
        .map(u-> new Usuario(
            u.getId_user(),
            u.getNombre(),
            u.getApellido(),
            u.getRut(),
            u.getEmail(),
            u.getPassword(),
            u.isActivo()
        )).collect(Collectors.toList());
    }
}
