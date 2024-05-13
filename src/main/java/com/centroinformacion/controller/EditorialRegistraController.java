package com.centroinformacion.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.centroinformacion.entity.Editorial;
import com.centroinformacion.service.EditorialService;
import com.centroinformacion.util.AppSettings;

@RestController
@RequestMapping("/url/editorial")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class EditorialRegistraController {

	
	@Autowired
	private EditorialService service;
	
	@GetMapping
	@ResponseBody
	public List<Editorial> listar(){
		return service.listarEditorial();
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> registrar(@RequestBody Editorial obj){
		
		HashMap<String, Object> salida = new HashMap<>();
		
		obj.setFechaRegistro(new Date());
		obj.setFechaActualizacion(new Date());
		obj.setEstado(AppSettings.ACTIVO);
		
		Editorial objEditorial = service.registrarEditorial(obj);
		
		if (objEditorial == null) {
			salida.put("mensaje", "Error en el registro");
		} else {
			salida.put("mensaje", "Se registro la Editorial con el id: " + objEditorial.getIdEditorial());
		}
		
		return ResponseEntity.ok(salida);
	}
	
}
