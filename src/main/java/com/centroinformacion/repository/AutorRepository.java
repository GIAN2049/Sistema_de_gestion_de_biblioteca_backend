package com.centroinformacion.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centroinformacion.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer>{
	//JPQL ==> Java Persistence Query Language
	//JPQL ==> Query a nivel de clases

	@Query("select e from Autor e where e.nombres like ?1 ")
	public abstract List<Autor> listaPorNombreLike(String nombres);
	
	@Query("select e from Autor e where e.nombres = ?1 ")
	public abstract List<Autor> listaPorNombreIgualRegistra(String nombres);
	
	@Query("select e from Autor e where e.nombres = ?1 and e.idAutor != ?2 ")
	public abstract List<Autor> listaPorNombreIgualActualiza(String nombres, int idAutor);
	
	@Query("select e from Autor e where e.nombres like ?1 and "
			  + "e.apellidos like ?2 and "
			  + "e.estado = ?3 and "
			  + "e.fechaNacimiento >= ?4 and "
			  + "e.fechaNacimiento <= ?5 and "
			  + "e.orcid like ?6 and "
			  + "(?7 = -1 or e.pais.idPais = ?7) and "
			  + "(?8 = -1 or e.grado.idDataCatalogo = ?8) ")
	
public abstract List<Autor> listaConsultaCompleja(String nom, String ape, int estado,Date fdesde, Date fhasta, String orcid, int idPais, int idGrado);
}
