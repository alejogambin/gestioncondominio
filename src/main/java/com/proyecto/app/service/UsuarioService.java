package com.proyecto.app.service;

import java.util.List;

import com.proyecto.app.entity.Usuario;

public interface UsuarioService {
    public List<Usuario> findAll();

    public Usuario save(Usuario usuario);

    public Usuario findOne(Long id_user);

    public void delete(Long id_user);

}