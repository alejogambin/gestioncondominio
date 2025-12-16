package com.proyecto.app.service;

import java.util.List;

import com.proyecto.app.entity.Rol;

public interface RolService {
    public List<Rol> findAll();

    public Rol save(Rol rol);

    public Rol findOne(Long id);

    public void delete(Long id);
}
