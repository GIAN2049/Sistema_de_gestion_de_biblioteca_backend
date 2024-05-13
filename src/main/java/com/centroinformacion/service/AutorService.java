package com.centroinformacion.service;

import java.util.Date;
import java.util.List;

import com.centroinformacion.entity.Autor;

public interface AutorService {
	public abstract List<Autor>listaAutor();
	public abstract Autor insertaAutor(Autor obj);
	
	
	//Para el crud
	public abstract Autor insertaActualizaAutor(Autor obj);
	public abstract List<Autor> listaAutorPorNombreLike(String nombres);
	public abstract void eliminaAutor(int idAutor);
	public abstract List<Autor> listaTodos();
	
	//Validaciones Para Revistrar
	public abstract List<Autor> listaAutorPorNombreIgualRegistra(String nombres);
	
	//Validaciones Para Actualizar
	public abstract List<Autor> listaAutorPorNombreIgualActualiza(String nombres, int idAutor);
	
	//Validaciones para Consultar
		public abstract List<Autor> listaConsultaCompleja(String nom, String ape, int estado,Date fdesde, Date fhasta,String orcid,int idPais, int idGrado);
}
