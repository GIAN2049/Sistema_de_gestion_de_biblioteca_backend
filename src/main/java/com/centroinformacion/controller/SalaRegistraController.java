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

import com.centroinformacion.entity.Sala;
import com.centroinformacion.service.SalaService;
import com.centroinformacion.util.AppSettings;

@RestController
@RequestMapping("/url/sala")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class SalaRegistraController {
	
	@Autowired
	private SalaService service;
	
	@GetMapping
	@ResponseBody
	public List<Sala> lista(){
		return service.listaSala();
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> registra(@RequestBody Sala obj){
		
		HashMap<String, Object> salida = new HashMap<>();
		obj.setFechaRegistro(new Date());
		obj.setFechaActualizacion(new Date());
		obj.setEstado(AppSettings.ACTIVO);
		
		Sala objSalida = service.insertaSala(obj);
		if (objSalida == null) {
			salida.put("mensaje","Error en el registro");
		}else {
			salida.put("mensaje","Se registró la Sala con el ID ==> " + objSalida.getIdSala());
		}
		return ResponseEntity.ok(salida);
	}
	
}
