package com.centroinformacion.repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.centroinformacion.entity.Sala;

public interface SalaRepository extends JpaRepository<Sala, Integer>{
	
	@Query("select a from Sala a where a.numero like ?1 ")
	public abstract List<Sala> listaPorNumeroLike(String numero);
	
	@Query("select a from Sala a where a.numero = ?1 ")
	public abstract List<Sala> listaPorNumeroIgualRegistra(String numero);
	
	@Query("select a from Sala a where a.numero = ?1 and a.idSala != ?2 ")
	public abstract List<Sala> listaPorNumeroIgualActualiza(String numero,int idSala);
	
	@Query("select a from Sala a where "
			+ " a.numero like ?1 and "
			+ " a.estado = ?2 and "
			+ " (?3 = -1 or a.sede.idDataCatalogo = ?3) and "
			+ " (?4 = -1 or a.tipoSala.idDataCatalogo = ?4) ")
public abstract List<Sala> listaConsultaCompleja(String numero,int estado,int idSede,int idTipo);
	
	
	@Query("Select x from Sala x where recursos like :var_filtro")
	public abstract List<Sala> listaSala(@Param("var_filtro") String filtro, Pageable ageable);
}
