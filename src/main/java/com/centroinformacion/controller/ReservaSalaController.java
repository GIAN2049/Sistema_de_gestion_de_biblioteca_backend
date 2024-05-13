package com.centroinformacion.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

import com.centroinformacion.entity.Alumno;
import com.centroinformacion.entity.ReservaSala;
import com.centroinformacion.entity.Sala;
import com.centroinformacion.entity.Usuario;
import com.centroinformacion.service.AlumnoService;
import com.centroinformacion.service.ReservaSalaService;
import com.centroinformacion.service.SalaService;
import com.centroinformacion.util.AppSettings;


@RestController
@RequestMapping("/url/reservaSala")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ReservaSalaController {
	@Autowired
	private ReservaSalaService service;
	
	@Autowired
	private AlumnoService alumnoService;
	
	@Autowired 
	private SalaService salaService;

	@ResponseBody
	@GetMapping("/listaAlumno/{filtro}")
	public List<Alumno> listarAlumno(
			@PathVariable("filtro")String filtro,
			@RequestParam(name = "page" , defaultValue = "0" , required = false) int page,
			@RequestParam(name = "size" , defaultValue = "5" , required = false) int size){
		Pageable paginable = PageRequest.of(page, size);
		List<Alumno> lstSalida = alumnoService.listaAlumno(filtro.equals("todos")?"%":filtro+"%", paginable);
		return lstSalida;
	}
	
	@ResponseBody
	@GetMapping("/listaSala/{filtro}")
	public List<Sala> listarSala(
			@PathVariable("filtro")String filtro,
			@RequestParam(name = "page" , defaultValue = "0" , required = false) int page,
			@RequestParam(name = "size" , defaultValue = "5" , required = false) int size){
		Pageable paginable = PageRequest.of(page, size);
		List<Sala> lstSalida = salaService.listaSalaPage(filtro.equals("todos")?"%":filtro+"%", paginable);
		return lstSalida;
	}
	
	@GetMapping("/listarReservaSala")
	public List<ReservaSala> listarReservaSala(){
		List<ReservaSala> rsala = service.listReservaSala();
		return rsala;
	}
	
	
	@ResponseBody
	@PostMapping("/registrarReserva")
	public HashMap<String, Object> registrarReserva(@RequestBody ReservaSala objReserva){
		HashMap<String, Object> mapSalida = new HashMap<String, Object>();
		
		objReserva.setEstado(AppSettings.ACTIVO);
		objReserva.setFechaReserva(new Date());
		objReserva.setFechaRegistro(new Date());
		
		ReservaSala objSalida = service.reservarSala(objReserva);
		
		if (objSalida != null) {
			mapSalida.put("mensaje", "Se registró la Reserva de sala con id:  ==> " + objReserva.getIdReservaSala());
			mapSalida.put("data", objReserva);
		}else {
			mapSalida.put("mensaje", "Error en el registro de la Reserva de Sala");
		}
		
		System.out.println("idSala: " + objReserva.getSala().getIdSala());
		System.out.println("idAlumno: " + objReserva.getAlumno().getIdAlumno());
		System.out.println("idUsuario: " + objReserva.getUsuarioRegistro().getIdUsuario());
		
		System.out.println("idSala: " + objSalida.getSala().getIdSala());
		System.out.println("idAlumno: " + objSalida.getAlumno().getIdAlumno());
		System.out.println("idUsuario: " + objSalida.getUsuarioRegistro().getIdUsuario());
		
		return mapSalida;
		
	}
}
