package com.centroinformacion.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.centroinformacion.entity.Alumno;

public interface AlumnoService {
	
	
	//LISTAR ALUMNOS
	
	
	//INSERTAR ALUMNOS
	public abstract Alumno insertarAlumno(Alumno obj);
	
	
	//Para el crud
	public abstract Alumno insertaActualizaAlumno(Alumno obj);
	public abstract List<Alumno> listaAlumnoPorNombreLike(String nombres);
	public abstract void eliminaAlumno(int idAlumno);
	public abstract List<Alumno> listaTodos();
	
	//Validaciones Para Revistrar
	public abstract List<Alumno> listaAlumnoPorNombreIgualRegistra(String nombres);
	
	//Validaciones Para Actualizar
	public abstract List<Alumno> listaAlumnoPorNombreIgualActualiza(String nombres, int idAlumno);
	
	//Para la consulta
	public abstract List<Alumno> listaConsultaCompleja(
			String nombres, String apellidos, 
			String telefono, String celular, String dni, String correo, String tipoSangre,
			Date fecIni, Date fecFin, int estado, int idPais, int idModalidad
			);
	
	//Proyecto
	
	public abstract List<Alumno> listaAlumno(String filtro, Pageable ageable) ;
	
}
