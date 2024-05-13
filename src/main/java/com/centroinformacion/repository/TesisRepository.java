package com.centroinformacion.repository;


import com.centroinformacion.entity.Tesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TesisRepository extends JpaRepository <Tesis,Integer> {
    @Query("select e from Tesis e where e.titulo like ?1 ")
    public abstract List<Tesis> listaPorNombreLike(String titulo);

    @Query("select e from Tesis e where e.titulo = ?1 ")
    public abstract List<Tesis> listaPorNombreIgualRegistra(String titulo);

    @Query("select e from Tesis e where e.titulo = ?1 and e.idTesis != ?2 ")
    public abstract List<Tesis> listaPorNombreIgualActualiza(String titulo, int idTesis);
@Query("select e from Tesis e where "
			+ " e.titulo like ?1 and "
			+ " e.fechaCreacion >=  ?2 and "
			+ " e.fechaCreacion <=  ?3 and "
			+ " e.estado = ?4 and "
			+ " (?5 = -1 or e.tema.idDataCatalogo = ?5) and "
			+ " (?6 = -1 or e.idioma.idDataCatalogo = ?6) and "
			+ " (?7 = -1 or e.centroEstudios.idDataCatalogo = ?7) ")
	public abstract List<Tesis> listaConsultaCompleja(
        String titulo, Date fecIni, Date fecFin, int estado, int idTema, int idIdioma, int idCentroEstudios);
}
