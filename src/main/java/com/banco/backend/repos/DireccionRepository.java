package com.banco.backend.repos;

import com.banco.backend.models.entities.Direccion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("DireccionRepository")
public interface DireccionRepository extends CrudRepository<Direccion,Integer> {
}
