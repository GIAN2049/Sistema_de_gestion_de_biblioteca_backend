package com.centroinformacion.service;

import java.util.Date;
import java.util.List;

import com.centroinformacion.entity.Editorial;

public interface EditorialService {
	//PC1
	
	public abstract List<Editorial> listarEditorial();
	public abstract Editorial registrarEditorial(Editorial editorial);
	
	//PC2
	
	public abstract Editorial insertaActualizaEditorial(Editorial obj);
	public abstract List<Editorial> listaEditorialPorRazonLike(String razon);
	public abstract void elimnaEditorial(int idEditorial);
	public abstract List<Editorial> listaTodos();
		
	//VALIDACIONES
	
	public abstract List<Editorial> listaEditorialPorRazonIgualRegistra(String razon);
	public abstract List<Editorial> listaEditorialPorRazonIgualActualiza(String razon, int idEditorial);
	
	//EF
	
	//CONSULTA
	
	public abstract List<Editorial> listaConsultaCompleja(String razonSocial, String direccion, 
			  											  String ruc, String gerente, Date fdesde, 
			  											  Date fhasta,  int estado, int idPais);
	
		
}
