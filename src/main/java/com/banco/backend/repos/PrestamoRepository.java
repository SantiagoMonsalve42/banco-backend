package com.banco.backend.repos;

import com.banco.backend.models.entities.Prestamo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("PrestamoRepository")
public interface PrestamoRepository extends CrudRepository<Prestamo,Integer> {
}
