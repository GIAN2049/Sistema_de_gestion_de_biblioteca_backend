package com.centroinformacion.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.centroinformacion.entity.Libro;

public interface LibroService {

	public abstract Libro insertaLibro(Libro obj);
	public abstract List<Libro> listaDataCatalogo();	
	public abstract List<Libro> listaLibroPorTituloLike(String titulo);
	
	public abstract void eliminaLibro(int idLibro);
	public abstract List<Libro> listaTodos();
	
	public abstract List<Libro> listaLibroPorTituloIgualRegistra(String titulo);
	
	//Validaciones Para Actualizar
	public abstract List<Libro> listaLibroPorTituloIgualActualiza(String titulo, int idLibro);
	
	//Para la Consulta
	public abstract List<Libro> listaConsultaCompleja(String titu, String seri, int estado, int idCategoria, int idEditorial, int idTipo);
	
	
	public List<Libro> listaLibro(String filtro, Pageable pageable);
	

}
