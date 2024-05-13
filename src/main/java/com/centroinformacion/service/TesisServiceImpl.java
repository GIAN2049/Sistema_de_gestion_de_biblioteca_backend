package com.centroinformacion.service;

import com.centroinformacion.entity.Tesis;
import com.centroinformacion.repository.TesisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TesisServiceImpl implements TesisService{
    @Autowired
    TesisRepository tesisRepository;
    @Override
    public Tesis insertaActualizaTesis(Tesis obj) {
        return tesisRepository.save(obj);
    }

    @Override
    public List<Tesis> listaTesis() {
        return tesisRepository.findAll();
    }

    @Override
    public List<Tesis> listaTesisPorNombreLike(String titulo) {
        return tesisRepository.listaPorNombreLike(titulo);
    }

    @Override
    public void eliminaRevista(int idTesis) {
        tesisRepository.deleteById(idTesis);
    }

    @Override
    public List<Tesis> listaTesisPorNombreIgualRegistra(String titulo) {
        return tesisRepository.listaPorNombreIgualRegistra(titulo);
    }

    @Override
    public List<Tesis> listaTesisPorNombreIgualActualiza(String titulo, int idTesis) {
        return tesisRepository.listaPorNombreIgualActualiza(titulo, idTesis);
    }

    @Override
    public List<Tesis> listaConsultaCompleja(String titulo, Date fecIni, Date fecFin, int estado, int idTema, int idIdioma, int idCentroEstudios) {
        return tesisRepository.listaConsultaCompleja(titulo, fecIni, fecFin, estado,idTema, idIdioma, idCentroEstudios);
    }
}
