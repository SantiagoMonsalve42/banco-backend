package com.banco.backend.repos;

import com.banco.backend.models.entities.Prestamo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("PrestamoRepository")
public interface PrestamoRepository extends CrudRepository<Prestamo,Integer> {
    @Query("select p from Prestamo p where p.cuenta.cliente.id = ?1")
    Iterable<Prestamo> readByClientId(Integer id);
}
