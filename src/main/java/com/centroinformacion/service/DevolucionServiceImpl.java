package com.centroinformacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centroinformacion.entity.Devolucion;
import com.centroinformacion.entity.DevolucionHasLibro;
import com.centroinformacion.repository.DevolucionHasLibroRepository;
import com.centroinformacion.repository.DevolucionRepository;

import jakarta.transaction.Transactional;

@Service
public class DevolucionServiceImpl implements DevolucionService{
	

	@Autowired
	private DevolucionRepository devolucionRepository;
	
	@Autowired
	private DevolucionHasLibroRepository detalleRepository;

	@Override
	public List<Devolucion> listaDevolucion() {
		return devolucionRepository.findAll();
	}

	@Override
	@Transactional
	public Devolucion insertaDevolucion(Devolucion obj) {
		Devolucion cabecera = devolucionRepository.save(obj); 
		for (DevolucionHasLibro d : cabecera.getDetallesDevolucion()) {
			d.getDevolucionHasLibroPK().setIdDevolucion(cabecera.getIdDevolucion());
			detalleRepository.save(d);
		}
		return cabecera;
	}

}
