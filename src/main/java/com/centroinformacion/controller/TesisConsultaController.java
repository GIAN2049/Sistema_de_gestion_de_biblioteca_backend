package com.centroinformacion.controller;

import com.centroinformacion.entity.Tesis;
import com.centroinformacion.service.TesisService;
import com.centroinformacion.util.AppSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/url/consultaTesis")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class TesisConsultaController {
    @Autowired
    private TesisService service;
    @GetMapping("/consultaTesisPorParametros")
	@ResponseBody
	public ResponseEntity<?> consultaAlumnoPorParametros(
			@RequestParam(name = "titulo" , required = true , defaultValue = "") String titulo,
			@RequestParam(name = "fecDesde" , required = true , defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecDesde,
			@RequestParam(name = "fecHasta" , required = true , defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecHasta,
			@RequestParam(name = "estado" , required = true , defaultValue = "") int estado,
			@RequestParam(name = "idTema" , required = false , defaultValue = "-1") int idTema,
			@RequestParam(name = "idIdioma" , required = false , defaultValue = "-1") int idIdioma,
			@RequestParam(name = "idCentroEstudios" , required = false , defaultValue = "-1") int idCentroEstudios
			){
		List<Tesis> lstSalida = service.listaConsultaCompleja("%"+titulo+"%",fecDesde, fecHasta, estado, idTema, idIdioma,idCentroEstudios);

		return ResponseEntity.ok(lstSalida);
	}
}
