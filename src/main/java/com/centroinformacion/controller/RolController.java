package com.centroinformacion.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centroinformacion.entity.Rol;
import com.centroinformacion.entity.UsuarioHasRol;
import com.centroinformacion.entity.UsuarioHasRolPK;
import com.centroinformacion.service.UsuarioService;
import com.centroinformacion.util.AppSettings;

@Controller
@RequestMapping("/url/asignacionRol")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class RolController {

	//Se agregó todo el controller
	@Autowired
	private UsuarioService usuarioService;
	
	@ResponseBody
	@GetMapping("/listaRolPorUsuario/{id}")
	public List<Rol> listaRolPorUsuario(@PathVariable("id")int idUsuario){
		return  usuarioService.traerRolesDeUsuario(idUsuario);
	}
	
	
	
	@ResponseBody
	@GetMapping("/registraRol")
	public HashMap<String, Object> registro(
			@RequestParam(name = "idUsuario" , defaultValue = "-1" , required = true)int idUsuario, 
			@RequestParam(name = "idRol" , defaultValue = "-1" , required = true)int idRol){
		HashMap<String, Object> maps = new HashMap<String, Object>();
		UsuarioHasRolPK pk = new UsuarioHasRolPK();
		pk.setIdRol(idRol);
		pk.setIdUsuario(idUsuario);

		UsuarioHasRol obj = new UsuarioHasRol();
		obj.setUsuarioHasRolPk(pk);
		
		Optional<UsuarioHasRol> existentRol = usuarioService.buscaRol(pk);
        if (existentRol.isEmpty()) {
        	UsuarioHasRol objSalida = usuarioService.insertaRol(obj);
        	if (objSalida == null) {
        		maps.put("mensaje", "Error en el registro");		
        	}else {
        		maps.put("mensaje", "Registro exitoso");
        	}
        }else {
        	maps.put("mensaje", "Ya existe el rol");
        }
        List<Rol> lstRol =  usuarioService.traerRolesDeUsuario(idUsuario);
        maps.put("lista", lstRol);
		return maps;
	}
	
	@ResponseBody
	@GetMapping("/eliminaRol")
	public HashMap<String, Object> elimina(
			@RequestParam(name = "idUsuario" , defaultValue = "-1" , required = true)int idUsuario, 
			@RequestParam(name = "idRol" , defaultValue = "-1" , required = true)int idRol){
		HashMap<String, Object> maps = new HashMap<String, Object>();
		
		//PK
		UsuarioHasRolPK pk = new UsuarioHasRolPK();
		pk.setIdRol(idRol);
		pk.setIdUsuario(idUsuario);
		
		//Detalle se le pasa la PK
		UsuarioHasRol obj = new UsuarioHasRol();
		obj.setUsuarioHasRolPk(pk);
		
		usuarioService.eliminaRol(obj);
		maps.put("mensaje", "Eliminación exitosa");
		
		List<Rol> lstRol =  usuarioService.traerRolesDeUsuario(idUsuario);
        maps.put("lista", lstRol);

		return maps;
	}
}
