package com.centroinformacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.centroinformacion.entity.Prestamo;
import com.centroinformacion.entity.PrestamoHasLibro;
import com.centroinformacion.repository.PrestamoHasLibroRepository;
import com.centroinformacion.repository.PrestamoRepository;

@Service
public class PrestamoServiceImpl implements PrestamoService{
	
	@Autowired
	private PrestamoRepository prestamoRepository;
	
	@Autowired
	private PrestamoHasLibroRepository detalleRepository;
	
	@Override
	@Transactional
	public Prestamo insertaPrestamo(Prestamo obj) {
		Prestamo cabecera = prestamoRepository.save(obj);
		for (PrestamoHasLibro d : cabecera.getDetallesPrestamo()) {
			d.getPrestamoHasLibroPK().setIdPrestamo(cabecera.getIdPrestamo());
		}
		return cabecera;
	}
	
	@Override
	public List<Prestamo> listaPrestamo() {
		return prestamoRepository.findAll();
	}
}
