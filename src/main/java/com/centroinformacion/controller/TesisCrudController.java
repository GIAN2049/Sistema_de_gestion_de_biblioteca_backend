package com.centroinformacion.controller;

import com.centroinformacion.entity.Tesis;
import com.centroinformacion.service.TesisService;
import com.centroinformacion.util.AppSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/url/crudTesis")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class TesisCrudController {
    @Autowired
    private TesisService tesisService;

    @GetMapping("/listaTesisPorNombreLike/{var}")
    @ResponseBody
    public ResponseEntity<?> listaTesisPorNombreLike(@PathVariable("var") String titulo){
        List<Tesis> lstSalida = null;
        if (titulo.equals("todos")) {
            lstSalida = tesisService.listaTesis();
        }else {
            lstSalida = tesisService.listaTesisPorNombreLike(titulo +  "%");
        }
        return ResponseEntity.ok(lstSalida);
    }
    @DeleteMapping("/eliminaTesis/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> eliminaRevista(@PathVariable("id") int idRevista) {
        Map<String, Object> salida = new HashMap<>();
        try {
            tesisService.eliminaRevista(idRevista);
            salida.put("mensaje", AppSettings.MENSAJE_ELI_EXITOSO);
        } catch (Exception e) {
            e.printStackTrace();
            salida.put("mensaje", AppSettings.MENSAJE_ELI_ERROR);
        }
        return ResponseEntity.ok(salida);
    }
    @PostMapping("/registraTesis")
    @ResponseBody
    public ResponseEntity<?> insertatTesis(@RequestBody Tesis obj) {
        Map<String, Object> salida = new HashMap<>();
        try {
            obj.setIdTesis(0);
            obj.setFechaActualizacion(new Date());
            obj.setFechaRegistro(new Date());
            obj.setEstado(AppSettings.ACTIVO);

            List<Tesis> lstBusqueda = tesisService.listaTesisPorNombreIgualRegistra(obj.getTitulo());
            if(!lstBusqueda.isEmpty()) {
                salida.put("mensaje", "La Tesis " + obj.getTitulo() + " ya existe");
                return ResponseEntity.ok(salida);
            }

            Tesis objSalida =  tesisService.insertaActualizaTesis(obj);
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
    @PutMapping("/actualizaTesis")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> actualizaTesis(@RequestBody Tesis obj) {
        Map<String, Object> salida = new HashMap<>();
        try {

            obj.setFechaActualizacion(new Date());

            List<Tesis> lstBusqueda = tesisService.listaTesisPorNombreIgualActualiza(obj.getTitulo(), obj.getIdTesis());
            if(!lstBusqueda.isEmpty()) {
                salida.put("mensaje", "La Tesis " + obj.getTitulo() + " ya existe");
                return ResponseEntity.ok(salida);
            }

            Tesis objSalida =  tesisService.insertaActualizaTesis(obj);
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
