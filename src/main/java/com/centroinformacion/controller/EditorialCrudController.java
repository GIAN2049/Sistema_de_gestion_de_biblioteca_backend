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

import com.centroinformacion.entity.Editorial;
import com.centroinformacion.service.EditorialService;
import com.centroinformacion.util.AppSettings;

@RestController
@RequestMapping("url/crudEditorial")
@CrossOrigin(origins= AppSettings.URL_CROSS_ORIGIN)
public class EditorialCrudController {

	@Autowired
	private EditorialService service;
		
	@GetMapping("/listaEditorialPorRazonLike/{var}")
	@ResponseBody
	public ResponseEntity<?> listaEditorialPorRazonLike(@PathVariable("var") String razon){
		List<Editorial> lstSalida = null;
		if (razon.equals("todos")) {
			lstSalida = service.listaTodos();
		}else {
			lstSalida = service.listaEditorialPorRazonLike(razon +  "%");
		}
		return ResponseEntity.ok(lstSalida);
	}
	


	@DeleteMapping("/eliminarEditorial/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminarEditorial(@PathVariable("id") int idEditorial) {
		Map<String, Object> salida = new HashMap<>();
		try {
			service.elimnaEditorial(idEditorial);
			salida.put("mensaje", AppSettings.MENSAJE_ELI_EXITOSO);
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ELI_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	

	
	@PostMapping("/registraEditorial")
	@ResponseBody
	public ResponseEntity<?> insertaEditorial(@RequestBody Editorial obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdEditorial(0);
			obj.setFechaActualizacion(new Date());
			obj.setFechaRegistro(new Date());
			obj.setEstado(AppSettings.ACTIVO);
			
			List<Editorial> lstBusqueda = service.listaEditorialPorRazonIgualRegistra(obj.getRazonSocial());
			if(!lstBusqueda.isEmpty()) {
				salida.put("mensaje", "La Editorial " + obj.getRazonSocial() + " ya existe");
				return ResponseEntity.ok(salida);
			}
			
			Editorial objSalida =  service.insertaActualizaEditorial(obj);
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
	

	
	@PutMapping("/actualizaEditorial")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaEditorial(@RequestBody Editorial obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			
			obj.setFechaActualizacion(new Date());
			
			List<Editorial> lstBusqueda = service.listaEditorialPorRazonIgualActualiza(obj.getRazonSocial(), obj.getIdEditorial());
			if(!lstBusqueda.isEmpty()) {
				salida.put("mensaje", "La Editorial " + obj.getRazonSocial() + " ya existe");
				return ResponseEntity.ok(salida);
			}
			
			Editorial objSalida =  service.insertaActualizaEditorial(obj);
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
