package com.proyecto.app.service.impl;

import java.util.List;
import java.util.Set;
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
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException{
        Usuario usuario = usuarioDao.findByEmail(correo).orElseThrow(()->
            new UsernameNotFoundException("Usuario no encontrado con email: "+correo));
        if (!usuario.isActivo()){
            throw new UsernameNotFoundException("La cuenta de "+ correo+ "esta desactivada. contacte al administrador.");
        }
        Set<GrantedAuthority> authorities = usuario.getRoles().stream()
        .map( rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toSet());

       

        if (authorities.isEmpty()) {
            throw new UsernameNotFoundException("el usuario "+correo+"no tiene roles adignados");
        }

        return User.builder()
            .username(usuario.getCorreo())
            .password(usuario.getContraseña())
            .authorities(authorities)
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(!usuario.isActivo())
            .build();           
    }
}
