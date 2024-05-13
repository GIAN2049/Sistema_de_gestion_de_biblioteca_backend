package com.centroinformacion.repository;

import com.centroinformacion.entity.Opcion;
import org.springframework.data.jpa.repository.JpaRepository;

import com.centroinformacion.entity.Rol;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    @Query("Select r.opcion from RolHasOpcion r where r.rol.idRol = ?1")
    public abstract List<Opcion> traerOpcionDeRol(int idRol);

}
