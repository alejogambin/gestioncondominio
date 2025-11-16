package com.proyecto.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.app.DAO.ITorreDao;
import com.proyecto.app.entity.Torre;
import com.proyecto.app.service.TorreService;

import jakarta.transaction.Transactional;

@Service
public class TorreServiceImpl implements TorreService {
    @Autowired
    private ITorreDao torreDao;
    
    @Transactional
    public Torre save(Torre torre) {
        return torreDao.save(torre);
    }
    @Transactional
    public void delete(Long id_torre) {
        torreDao.deleteById(id_torre);
    }
    @Transactional
    public Torre findOne(Long id_torre) {
        return torreDao.findById(id_torre).orElse(null);
    }
    @Transactional
    public List<Torre> findAll() {
        return (List<Torre>) torreDao.findAll();
    }
}
