package com.centroinformacion.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import com.centroinformacion.entity.Sala;

public interface SalaService {

	public abstract List<Sala> listaSala();
	public abstract Sala insertaSala(Sala obj);
	
	//pc2
	
	public abstract Sala insertaActualizaSala(Sala obj);
	public abstract List<Sala> listaSalaPorNumeroLike(String numero);
	public abstract void eliminaSala(int idSala);
	
	public abstract List<Sala> listaSalaPorNumeroIgualRegistra(String numero);
	public abstract List<Sala> listaSalaPorNumeroIgualActualiza(String numero, int idSala);
	
	//pc3
	
	public abstract List<Sala> listaConsultaCompleja(String numero,int estado,int idSede,int idTipo);
	
	
	public abstract List<Sala> listaSalaPage(String filtro, Pageable ageable);
}
