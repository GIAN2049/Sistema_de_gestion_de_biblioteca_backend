package com.centroinformacion.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.centroinformacion.entity.Alumno;
import com.centroinformacion.entity.Devolucion;
import com.centroinformacion.entity.Libro;
import com.centroinformacion.service.AlumnoService;
import com.centroinformacion.service.DevolucionService;
import com.centroinformacion.service.LibroService;
import com.centroinformacion.util.AppSettings;

@RestController
@RequestMapping("/url/devolucion")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class DevolucionController {
	
	@Autowired
	private AlumnoService alumnoService;
	@Autowired
	private LibroService libroService;
	@Autowired
	private DevolucionService devolucionService;
	
	@ResponseBody
	@GetMapping("/listaAlumno/{filtro}")
	public List<Alumno> listaAlumno(
			@PathVariable("filtro")String filtro,
			@RequestParam(name = "page" , defaultValue = "0" , required = false) int page,
			@RequestParam(name = "size" , defaultValue = "5" , required = false) int size){
		Pageable paginable = PageRequest.of(page, size);
		List<Alumno> lstSalida = alumnoService.listaAlumno(filtro.equals("todos")?"%":filtro+"%", paginable);
		return lstSalida;
	}
	
	@ResponseBody
	@GetMapping("/listaLibro/{filtro}")
	public List<Libro> listaLibro(
			@PathVariable("filtro")String filtro,
			@RequestParam(name = "page" , defaultValue = "0" , required = false) int page,
			@RequestParam(name = "size" , defaultValue = "5" , required = false) int size){
		Pageable paginable = PageRequest.of(page, size);
		List<Libro> lstSalida = libroService.listaLibro(filtro.equals("todos")?"%":filtro+"%", paginable);
		return lstSalida;
	}
	
	@ResponseBody
	@PostMapping("/registraDevolucion")
	public HashMap<String, Object> registraDevolucion(@RequestBody Devolucion objDevolucion){
		HashMap<String, Object> mapSalida = new HashMap<String, Object>();
		objDevolucion.setFechaRegistro(new Date());
		Devolucion objDevolucionSalida = devolucionService.insertaDevolucion(objDevolucion);
		if (objDevolucionSalida != null) {
			mapSalida.put("mensaje", "Se registró la Boleta ==> " + objDevolucionSalida.getIdDevolucion());
			mapSalida.put("data", objDevolucionSalida);
		}else {
			mapSalida.put("mensaje", "Error en el registro de Devolución");
		}
		return mapSalida;
	}
	
}

