package com.proyecto.app.DAO;

import org.springframework.data.repository.CrudRepository;

import com.proyecto.app.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
    
    
}
