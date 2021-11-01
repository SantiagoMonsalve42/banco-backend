package com.banco.backend.repos;

import com.banco.backend.models.entities.Ciudad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("CiudadRepository")
public interface CiudadRepository extends CrudRepository<Ciudad,Integer> {
    @Query("select c from Ciudad c where c.departamento.id = ?1")
    Iterable<Ciudad> obtenerPorIdDepartamento(Integer id);
}
