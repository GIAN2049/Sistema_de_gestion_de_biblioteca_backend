package com.centroinformacion.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.centroinformacion.entity.Alumno;
import com.centroinformacion.repository.AlumnoRepository;

@Service
public class AlumnoServiceImp implements AlumnoService {

	@Autowired
	private AlumnoRepository repository;

	@Override
	public List<Alumno> listaTodos() {
		return repository.findAll();
	}

	@Override
	public Alumno insertarAlumno(Alumno obj) {
		// TODO Auto-generated method stub
		return repository.save(obj);
	}

	@Override
	public Alumno insertaActualizaAlumno(Alumno obj) {
		// TODO Auto-generated method stub
		return repository.save(obj);
	}

	@Override
	public List<Alumno> listaAlumnoPorNombreLike(String nombres) {
		// TODO Auto-generated method stub
		return repository.listaPorNombreLike(nombres);
	}

	@Override
	public void eliminaAlumno(int idAlumno) {
		// TODO Auto-generated method stub
		repository.deleteById(idAlumno);
		
	}

	@Override
	public List<Alumno> listaAlumnoPorNombreIgualRegistra(String nombres) {
		// TODO Auto-generated method stub
		return repository.listaPorNombreIgualRegistra(nombres);
	}

	@Override
	public List<Alumno> listaAlumnoPorNombreIgualActualiza(String nombres, int idAlumno) {
		// TODO Auto-generated method stub
		return repository.listaPorNombreIgualActualiza(nombres, idAlumno);
	}

	@Override
	public List<Alumno> listaConsultaCompleja(String nombres, String apellidos, String telefono, String celular,
			String dni, String correo, String tipoSangre, Date fecIni, Date fecFin, int estado, int idPais,
			int idModalidad) {
		// TODO Auto-generated method stub
		return repository.listaConsultaCompleja(nombres, apellidos, telefono, celular, dni, correo, tipoSangre, fecIni, fecFin, estado, idPais, idModalidad);
	}

	@Override
	public List<Alumno> listaAlumno(String filtro, Pageable ageable) {
		return repository.listaAlumno(filtro, ageable);
	}
	
	

	
}
