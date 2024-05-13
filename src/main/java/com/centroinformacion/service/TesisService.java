package com.centroinformacion.service;

import com.centroinformacion.entity.Tesis;

import java.util.Date;
import java.util.List;

public interface TesisService {
    public abstract Tesis insertaActualizaTesis(Tesis obj);
	public abstract List<Tesis> listaTesis();
	public abstract List<Tesis> listaTesisPorNombreLike(String titulo);
	public abstract void eliminaRevista(int idTesis);

	//Validaciones Para Revistrar
	public abstract List<Tesis> listaTesisPorNombreIgualRegistra(String titulo);

	//Validaciones Para Actualizar
	public abstract List<Tesis> listaTesisPorNombreIgualActualiza(String titulo, int idTesis);

	public abstract List<Tesis> listaConsultaCompleja(String titulo, Date fecIni, Date fecFin, int estado, int idTema, int idIdioma, int idCentroEstudios);
}
