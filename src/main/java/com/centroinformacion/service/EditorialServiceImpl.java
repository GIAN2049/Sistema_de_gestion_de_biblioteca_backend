package com.centroinformacion.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centroinformacion.entity.Editorial;
import com.centroinformacion.repository.EditorialRepository;

@Service
public class EditorialServiceImpl implements EditorialService{

	
	@Autowired
	private EditorialRepository repository;
	
	@Override
	public List<Editorial> listarEditorial() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Editorial registrarEditorial(Editorial editorial) {
		// TODO Auto-generated method stub
		return repository.save(editorial);
	}

	@Override
	public Editorial insertaActualizaEditorial(Editorial obj) {
		// TODO Auto-generated method stub
		return repository.save(obj);
	}

	@Override
	public List<Editorial> listaEditorialPorRazonLike(String razon) {
		// TODO Auto-generated method stub
		return repository.listaPorRazonSocialLike(razon);
	}

	@Override
	public void elimnaEditorial(int idEditorial) {
		// TODO Auto-generated method stub
		repository.deleteById(idEditorial);
	}

	@Override
	public List<Editorial> listaTodos() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<Editorial> listaEditorialPorRazonIgualRegistra(String razon) {
		// TODO Auto-generated method stub
		return repository.listaPorRazonSocialIgualRegistra(razon);
	}

	@Override
	public List<Editorial> listaEditorialPorRazonIgualActualiza(String razon, int idEditorial) {
		// TODO Auto-generated method stub
		return repository.listaPorRazonSocialIgualActualiza(razon, idEditorial);
	}

	@Override
	public List<Editorial> listaConsultaCompleja(String razonSocial, String direccion, 
												 String ruc, String gerente, Date fdesde,
												 Date fhasta, int estado, int idPais) {
		// TODO Auto-generated method stub
		return repository.listaConsultaCompleja(razonSocial, direccion, ruc,
												gerente, fdesde, fhasta,
												estado, idPais);
	}

}
