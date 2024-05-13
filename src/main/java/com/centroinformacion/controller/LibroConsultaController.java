package com.centroinformacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.centroinformacion.entity.Libro;
import com.centroinformacion.service.LibroService;
import com.centroinformacion.util.AppSettings;

@RestController
@RequestMapping("/url/consultaLibro")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class LibroConsultaController {

	@Autowired
	private LibroService libroService;
	
	@GetMapping("/consultaLibroPorParametros")
	@ResponseBody
	public ResponseEntity<?> consultaLibro(
						@RequestParam(name = "titulo" , required = true , defaultValue = "") String titu, 						
						@RequestParam(name = "serie" , required = true , defaultValue = "")String seri,
						@RequestParam(name = "estado" , required = true , defaultValue = "")int estado,
						@RequestParam(name = "idCategoria" , required = false , defaultValue = "-1")int idCategoria, 
						@RequestParam(name = "idEditorial" , required = false , defaultValue = "-1")int idEditorial, 
						@RequestParam(name = "idTipo" , required = false , defaultValue = "-1")int idTipo){
		List<Libro> lstSalida = libroService.listaConsultaCompleja("%"+titu+"%", "%"+seri+"%", estado, idCategoria, idEditorial, idTipo);
		
		return ResponseEntity.ok(lstSalida);
	}
	//@RequestParam(name = "anio" , required = true , defaultValue = "") int ani,
}
