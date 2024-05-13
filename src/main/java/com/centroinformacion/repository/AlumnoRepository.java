package com.centroinformacion.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.centroinformacion.entity.Alumno;



public interface AlumnoRepository extends JpaRepository<Alumno, Integer>{
	
	public abstract List<Alumno> findByOrderByApellidosAsc();
	
	@Query("select e from Alumno e where e.nombres like ?1 ")
	public abstract List<Alumno> listaPorNombreLike(String nombres);
	
	@Query("select e from Alumno e where e.nombres = ?1 ")
	public abstract List<Alumno> listaPorNombreIgualRegistra(String nombres);
	
	@Query("select e from Alumno e where e.nombres = ?1 and e.idAlumno != ?2 ")
	public abstract List<Alumno> listaPorNombreIgualActualiza(String nombres, int idAlumno);
	
	
	@Query("select e from Alumno e where "
			+ " e.nombres like ?1 and "
			+ " e.apellidos like ?2 and "
			+ " e.telefono like ?3 and "
			+ " e.celular like ?4 and "
			+ " e.dni like ?5 and "
			+ " e.correo like ?6 and "
			+ " e.tipoSangre like ?7 and "
			+ " e.fechaNacimiento >=  ?8 and "
			+ " e.fechaNacimiento <=  ?9 and "
			+ " e.estado = ?10 and "
			+ " (?11 = -1 or e.pais.idPais = ?11) and "
			+ " (?12 = -1 or e.modalidad.idDataCatalogo = ?12) ")
	public abstract List<Alumno> listaConsultaCompleja(
			String nombres, String apellidos, 
			String telefono, String celular, String dni, String correo, String tipoSangre,
			Date fecIni, Date fecFin, int estado, int idPais, int idModalidad
			);
	
	@Query("Select x from Alumno x where nombres like :var_filtro or apellidos like :var_filtro")
	public abstract List<Alumno> listaAlumno(@Param("var_filtro") String filtro, Pageable ageable);
	
}