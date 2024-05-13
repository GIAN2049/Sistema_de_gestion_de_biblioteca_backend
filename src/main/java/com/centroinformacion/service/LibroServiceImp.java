package com.centroinformacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.centroinformacion.entity.Libro;
import com.centroinformacion.repository.LibroRepository;

@Service
public class LibroServiceImp implements LibroService {

	@Autowired
	private LibroRepository repository;

	@Override
	public List<Libro> listaDataCatalogo() {
		return repository.findAll();
	}

	@Override
	public Libro insertaLibro(Libro obj) {
		return repository.save(obj);
	}

	@Override
	public void eliminaLibro(int idLibro) {
		repository.deleteById(idLibro);
		
	}

	@Override
	public List<Libro> listaTodos() {
		return repository.findAll();
	}

	@Override
	public List<Libro> listaLibroPorTituloIgualRegistra(String titulo) {
		return repository.listaPorTituloIgualRegistra(titulo);
	}

	@Override
	public List<Libro> listaLibroPorTituloIgualActualiza(String titulo, int idLibro) {
		return repository.listaPorTituloIgualActualiza(titulo, idLibro);
	}

	@Override
	public List<Libro> listaLibroPorTituloLike(String titulo) {
		return repository.listaPorTitutloLike(titulo);
	}

	@Override
	public List<Libro> listaConsultaCompleja(String titu, String seri, int estado, int idCategoria,
			int idEditorial, int idTipo) {
		return repository.listaConsultaCompleja(titu, seri, estado, idCategoria, idEditorial, idTipo);
	}

	@Override
	public List<Libro> listaLibro(String filtro, Pageable pageable) {
		return repository.listaLibro(filtro, pageable);
	}

	
}
