package com.proyecto.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.app.DAO.IRolDao;
import com.proyecto.app.entity.Rol;
import com.proyecto.app.service.RolService;
@Service
public class RolServiceImpl implements RolService {
    @Autowired
    private IRolDao rolDao;
    @Override
    public List<Rol> findAll() {
       return (List<Rol>) rolDao.findAll();
    }

    @Override
    public Rol save(Rol rol) {
        return rolDao.save(rol);
    }

    @Override
    public Rol findOne(Long id) {
        return rolDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
       rolDao.deleteById(id);
    }
    
}
