package com.proyecto.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.proyecto.app.DAO.IUsuarioDao;
import com.proyecto.app.entity.Usuario;
import com.proyecto.app.service.UsuarioService;

import jakarta.transaction.Transactional;

public class UsuarioServiceImpl implements UsuarioService {
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
   
}
