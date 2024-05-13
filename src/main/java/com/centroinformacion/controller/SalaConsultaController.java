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
import com.centroinformacion.entity.Sala;
import com.centroinformacion.service.SalaService;
import com.centroinformacion.util.AppSettings;

@RestController
@RequestMapping("/url/consultaSala")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class SalaConsultaController {
	
	@Autowired
	private SalaService salaService;
	
	@GetMapping("/consultaSalaPorParametros")
	@ResponseBody
	public ResponseEntity<?> consultaSalaPorParametros(
			@RequestParam(name = "numero" , required = true , defaultValue = "") String numero,
			@RequestParam(name = "estado" , required = true , defaultValue = "") int estado,
			@RequestParam(name = "idSede" , required = true , defaultValue = "") int idSede,
			@RequestParam(name = "idTipo" , required = false , defaultValue = "-1") int idTipo
			){
		List<Sala> lstSalida = salaService.listaConsultaCompleja(
								"%"+numero+"%", estado, idSede, idTipo);
		
		return ResponseEntity.ok(lstSalida);
	}
	
}
