package com.centroinformacion.service;

import java.util.List;

import com.centroinformacion.entity.Prestamo;

public interface PrestamoService {
	public Prestamo insertaPrestamo(Prestamo obj);
	public List<Prestamo> listaPrestamo();
}
