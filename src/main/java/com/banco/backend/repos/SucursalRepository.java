package com.banco.backend.repos;

import com.banco.backend.models.entities.Sucursal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("SucursalRepository")
public interface SucursalRepository extends CrudRepository<Sucursal,Integer> {
}
