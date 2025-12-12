package com.proyecto.app.DAO;

import org.springframework.data.repository.CrudRepository;

import com.proyecto.app.entity.Usuario;

import java.util.Optional;


public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

    Optional<Usuario>  findByEmail(String email);

    Optional<Usuario> findByRut(String Rut);

    boolean existsByemail(String email);

    boolean existsByRut(String rut);

    
}
