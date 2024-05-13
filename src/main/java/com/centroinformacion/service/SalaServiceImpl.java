package com.centroinformacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.centroinformacion.entity.Sala;
import com.centroinformacion.repository.SalaRepository;

@Service
public class SalaServiceImpl implements SalaService{

	@Autowired
	private SalaRepository repository;
	
	@Override
	public List<Sala> listaSala() {
		return repository.findAll();
	}

	@Override
	public Sala insertaSala(Sala obj) {
		return repository.save(obj);
	}
	
	//pc2

	@Override
	public Sala insertaActualizaSala(Sala obj) {
		
		return repository.save(obj);
	}

	@Override
	public List<Sala> listaSalaPorNumeroLike(String numero) {
		return repository.listaPorNumeroLike(numero);
	}

	@Override
	public void eliminaSala(int idSala) {
		repository.deleteById(idSala);
	}

	@Override
	public List<Sala> listaSalaPorNumeroIgualRegistra(String numero) {
		return repository.listaPorNumeroIgualRegistra(numero);
	}

	@Override
	public List<Sala> listaSalaPorNumeroIgualActualiza(String numero, int idSala) {
		return repository.listaPorNumeroIgualActualiza(numero, idSala);
	}

	@Override
	public List<Sala> listaConsultaCompleja(String numero, int estado, int idSede, int idTipo) {
		return repository.listaConsultaCompleja(numero, estado, idSede, idTipo);
	}

	@Override
	public List<Sala> listaSalaPage(String filtro, Pageable ageable) {
		// TODO Auto-generated method stub
		return repository.listaSala(filtro, ageable);
	}

	
}
