package com.centroinformacion.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.centroinformacion.entity.*;
import com.centroinformacion.service.OpcionService;
import com.centroinformacion.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centroinformacion.service.UsuarioService;
import com.centroinformacion.util.AppSettings;

@Controller
@RequestMapping("/url/asignacionOpcion")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class OpcionController {

    //Se agregó todo el controller
    @Autowired
    private RolService rolService;

    @ResponseBody
    @GetMapping("/listaOpcionPorRol/{id}")
    public List<Opcion> listaOpcionPorRol(@PathVariable("id")int idRol){
        return  rolService.traerOpcionesDeRol(idRol);
    }



    @ResponseBody
    @GetMapping("/registraOpcion")
    public HashMap<String, Object> registro(
            @RequestParam(name = "idRol" , defaultValue = "-1" , required = true)int idRol,
            @RequestParam(name = "idOpcion" , defaultValue = "-1" , required = true)int idOpcion){
        HashMap<String, Object> maps = new HashMap<String, Object>();
        RolHasOpcionPK pk = new RolHasOpcionPK();
        pk.setIdRol(idRol);
        pk.setIdOpcion(idOpcion);

        RolHasOpcion obj = new RolHasOpcion();
        obj.setRolHasOpcionPK(pk);

        Optional<RolHasOpcion> existentRol = rolService.buscaOpcion(pk);
        if (existentRol.isEmpty()) {
            RolHasOpcion objSalida = rolService.insertaOpcion(obj);
            if (objSalida == null) {
                maps.put("mensaje", "Error en el registro");
            }else {
                maps.put("mensaje", "Registro exitoso");
            }
        }else {
            maps.put("mensaje", "Ya existe la opcion");
        }
        List<Opcion> lstOpcion =  rolService.traerOpcionesDeRol(idRol);
        maps.put("lista", lstOpcion);
        return maps;
    }

    @ResponseBody
    @GetMapping("/eliminaOpcion")
    public HashMap<String, Object> elimina(
            @RequestParam(name = "idRol" , defaultValue = "-1" , required = true)int idRol,
            @RequestParam(name = "idOpcion" , defaultValue = "-1" , required = true)int idOpcion){
        HashMap<String, Object> maps = new HashMap<String, Object>();

        //PK
        RolHasOpcionPK pk = new RolHasOpcionPK();
        pk.setIdRol(idRol);
        pk.setIdOpcion(idOpcion);

        //Detalle se le pasa la PK
        RolHasOpcion obj = new RolHasOpcion();
        obj.setRolHasOpcionPK(pk);

        rolService.eliminaOpcion(obj);
        maps.put("mensaje", "Eliminación exitosa");

        List<Opcion> lstOpcion =  rolService.traerOpcionesDeRol(idRol);
        maps.put("lista", lstOpcion);

        return maps;
    }
}
