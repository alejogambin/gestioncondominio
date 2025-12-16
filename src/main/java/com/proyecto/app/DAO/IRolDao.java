package com.proyecto.app.DAO;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.proyecto.app.entity.Rol;

public interface IRolDao extends CrudRepository<Rol, Long> {

    
}
