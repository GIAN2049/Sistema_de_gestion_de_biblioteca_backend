package com.centroinformacion.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.centroinformacion.entity.Autor;
import com.centroinformacion.service.AutorService;
import com.centroinformacion.util.AppSettings;

@RestController
@RequestMapping("/url/crudAutor")
@CrossOrigin(origins= AppSettings.URL_CROSS_ORIGIN)

public class AutorCrudController {
	@Autowired
	private AutorService autorService;
	
	/*LISTAR AUTOR*/
	
	@GetMapping("/listaAutorPorNombreLike/{var}")
	@ResponseBody
	public ResponseEntity<?> listaAutorPorNombreLike(@PathVariable("var") String nombres){
		List<Autor> lstSalida = null;
		if (nombres.equals("todos")) {
			lstSalida = autorService.listaTodos();
		}else {
			lstSalida = autorService.listaAutorPorNombreLike(nombres +  "%");
		}
		return ResponseEntity.ok(lstSalida);
	}
	
	/*ELIMINA AUTOR*/
	
	@DeleteMapping("/eliminaAutor/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaAutor(@PathVariable("id") int idAutor) {
		Map<String, Object> salida = new HashMap<>();
		try {
			autorService.eliminaAutor(idAutor);
			salida.put("mensaje", AppSettings.MENSAJE_ELI_EXITOSO);
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ELI_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	/*REGISTRA AUTOR*/
	
	@PostMapping("/registraAutor")
	@ResponseBody
	public ResponseEntity<?> insertaRevista(@RequestBody Autor obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdAutor(0);
			obj.setFechaActualizacion(new Date());
			obj.setFechaRegistro(new Date());
			obj.setEstado(AppSettings.ACTIVO);
			
			List<Autor> lstBusqueda = autorService.listaAutorPorNombreIgualRegistra(obj.getNombres());
			if(!lstBusqueda.isEmpty()) {
				salida.put("mensaje", "El Autor " + obj.getNombres() + " ya existe");
				return ResponseEntity.ok(salida);
			}
			
			Autor objSalida =  autorService.insertaActualizaAutor(obj);
			if (objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
			} else {
				salida.put("mensaje", AppSettings.MENSAJE_REG_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	/*ACTUALIZA AUTOR*/
	
	@PutMapping("/actualizaAutor")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaAutor(@RequestBody Autor obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			
			obj.setFechaActualizacion(new Date());
			
			List<Autor> lstBusqueda = autorService.listaAutorPorNombreIgualActualiza(obj.getNombres(), obj.getIdAutor());
			if(!lstBusqueda.isEmpty()) {
				salida.put("mensaje", "El Autor " + obj.getNombres() + " ya se ha actualizado");
				return ResponseEntity.ok(salida);
			}
			
			Autor objSalida =  autorService.insertaActualizaAutor(obj);
			if (objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
			} else {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}

}
