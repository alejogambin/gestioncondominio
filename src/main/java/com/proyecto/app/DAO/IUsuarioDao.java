package com.proyecto.app.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.app.entity.Usuario;

import java.util.List;
import java.util.Optional;


public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Optional<Usuario> findByEmail(@Param("email") String email);

    Optional<Usuario> findByRut(String Rut);

    boolean existsByEmail(String email);

    boolean existsByRut(String rut);

   
}
