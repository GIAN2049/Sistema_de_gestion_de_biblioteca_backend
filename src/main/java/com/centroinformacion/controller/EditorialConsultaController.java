package com.centroinformacion.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.centroinformacion.entity.Editorial;
import com.centroinformacion.service.EditorialService;
import com.centroinformacion.util.AppSettings;

@RestController
@RequestMapping("url/consultaEditorial")
@CrossOrigin(origins= AppSettings.URL_CROSS_ORIGIN)
public class EditorialConsultaController {
	
	@Autowired
	private EditorialService service;
	
	@GetMapping("/consultaEditorialPorParametros")
	@ResponseBody
	public ResponseEntity<?> consultaEditorial(
			@RequestParam(name = "razonSocial", required = true, defaultValue = "") String razonSocial,
			@RequestParam(name = "direccion", required = true, defaultValue = "") String direccion,
			@RequestParam(name = "ruc", required = true, defaultValue = "") String ruc,
			@RequestParam(name = "gerente", required = true, defaultValue = "") String gerente,
			@RequestParam(name = "fdesde", required = true, defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fdesde,
			@RequestParam(name = "fhasta", required = true, defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fhasta,
			@RequestParam(name = "estado", required = true, defaultValue = "") int estado,
			@RequestParam(name = "idPais", required = false, defaultValue = "") int idPais) {

		List<Editorial> lstSalida = service.listaConsultaCompleja("%" + razonSocial + "%", 
																  "%" + direccion + "%",
																  "%" + ruc + "%", 
																  "%" + gerente + "%", 
																  fdesde, fhasta, estado, idPais);

		return ResponseEntity.ok(lstSalida);
	}
	
}
