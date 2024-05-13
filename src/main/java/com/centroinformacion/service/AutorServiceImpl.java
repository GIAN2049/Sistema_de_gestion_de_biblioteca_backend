package com.centroinformacion.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centroinformacion.entity.Autor;
import com.centroinformacion.repository.AutorRepository;

@Service
public class AutorServiceImpl implements AutorService {

	@Autowired
	private AutorRepository repository;
	
	@Override
	public List<Autor> listaAutor() {
		return repository.findAll();
	}

	@Override
	public Autor insertaAutor(Autor obj) {
		return repository.save(obj);
	}
	
	@Override
	public List<Autor> listaTodos() {
		return repository.findAll();
	}
	
	@Override
	public Autor insertaActualizaAutor(Autor obj) {
		return repository.save(obj);
	}

	@Override
	public List<Autor> listaAutorPorNombreLike(String nombres) {
		return repository.listaPorNombreLike(nombres);
	}

	@Override
	public void eliminaAutor(int idAutor) {
		repository.deleteById(idAutor);
	}

	@Override
	public List<Autor> listaAutorPorNombreIgualRegistra(String nombres) {
		return repository.listaPorNombreIgualRegistra(nombres);
	}

	@Override
	public List<Autor> listaAutorPorNombreIgualActualiza(String nombres, int idAutor) {
		return repository.listaPorNombreIgualActualiza(nombres, idAutor);
	}

	@Override
	public List<Autor> listaConsultaCompleja(String nom, String ape, int estado,Date fdesde, Date fhasta,String orcid,int idPais, int idGrado) {
		return repository.listaConsultaCompleja(nom, ape,  estado, fdesde,  fhasta, orcid, idPais, idGrado);
	}

}