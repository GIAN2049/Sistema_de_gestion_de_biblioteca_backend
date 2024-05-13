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

import com.centroinformacion.entity.Autor;
import com.centroinformacion.service.AutorService;
import com.centroinformacion.util.AppSettings;

@RestController
@RequestMapping("/url/consultaAutor")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class AutorConsultaController {

	@Autowired
	private AutorService autorService;
	
	@GetMapping("/consultaAutorPorParametros")
	@ResponseBody
	public ResponseEntity<?> consultaAutor(
						@RequestParam(name = "nombres" , required = true , defaultValue = "") String nom,
						@RequestParam(name = "apellidos" , required = true , defaultValue = "") String ape,
						@RequestParam(name = "estado" , required = true , defaultValue = "")int estado,
						@RequestParam(name = "fecDesde" , required = true , defaultValue = "")@DateTimeFormat(pattern = "yyyy-MM-dd")Date fdesde, 
						@RequestParam(name = "fecHasta" , required = true , defaultValue = "")@DateTimeFormat(pattern = "yyyy-MM-dd")Date fhasta,
						@RequestParam(name = "orcid" , required = false , defaultValue = "") String orcid,
						@RequestParam(name = "idPais" , required = false , defaultValue = "-1")int idPais,
						@RequestParam(name = "idGrado" , required = false , defaultValue = "-1")int idGrado){
		
		List<Autor> lstSalida = autorService.listaConsultaCompleja("%"+nom+"%", "%"+ape+"%", estado, fdesde,  fhasta, "%"+orcid+"%",idPais,  idGrado);
		
		return ResponseEntity.ok(lstSalida);
	}
	
}