package com.proyecto.app.DAO;

import org.springframework.data.repository.CrudRepository;

import com.proyecto.app.entity.Torre;

public interface ITorreDao extends CrudRepository<Torre, Long>{
    
}
