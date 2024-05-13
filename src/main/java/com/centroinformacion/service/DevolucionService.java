package com.centroinformacion.service;

import java.util.List;

import com.centroinformacion.entity.Devolucion;

public interface DevolucionService {
	
	public List<Devolucion> listaDevolucion();
	public Devolucion insertaDevolucion(Devolucion objDevolucion);
	
}
