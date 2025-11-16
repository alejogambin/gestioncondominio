package com.proyecto.app.service;

import java.util.List;

import com.proyecto.app.entity.Torre;

public interface TorreService {
    public List<Torre> findAll();

    public Torre save(Torre torre);

    public Torre findOne(Long id_torre);

    public void delete(Long id_torre);

}
