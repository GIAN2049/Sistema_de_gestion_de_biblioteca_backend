package com.centroinformacion.service;

import com.centroinformacion.entity.Opcion;
import com.centroinformacion.repository.OpcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpcionServiceImpl implements  OpcionService{
    @Autowired
    private OpcionRepository repository;

    @Override
    public List<Opcion> listaOpcion() {
        return repository.findAll();
    }
}


