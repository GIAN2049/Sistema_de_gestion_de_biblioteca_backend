package com.centroinformacion.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.centroinformacion.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer>{
	
	@Query("select e from Libro e where e.titulo like ?1 ")
	public abstract List<Libro> listaPorTitutloLike(String titulo);
	
	@Query("select e from Libro e where e.titulo = ?1 ")
	public abstract List<Libro> listaPorTituloIgualRegistra(String titulo);
	
	//Valida que no se registren 2 libros con el mismo nombre
	@Query("select e from Libro e where e.titulo = ?1 and e.idLibro != ?2 ")
	public abstract List<Libro> listaPorTituloIgualActualiza(String titutlo, int idLibro);
	
	//+ "e.anio = ?2 and "
	@Query("select e from Libro e where e.titulo like ?1 and "
			  + "e.serie like ?2 and "
			  + "e.estado = ?3 and "
			  + "(?4 = -1 or e.categoriaLibro.idDataCatalogo = ?4) and "
			  + "(?5 = -1 or e.editorial.idEditorial = ?5) and "
			  + "(?6 = -1 or e.tipoLibro.idDataCatalogo = ?6)" )
	public abstract List<Libro> listaConsultaCompleja(String titu, String seri, int estado, int idCategoria, int idEditorial, int idTipo);
	
	@Query("Select x from Libro x where x.titulo like :var_fil")
	public List<Libro> listaLibro(@Param("var_fil") String filtro, Pageable pageable);
}
