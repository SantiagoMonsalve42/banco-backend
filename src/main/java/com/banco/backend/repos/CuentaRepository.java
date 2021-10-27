package com.banco.backend.repos;

import com.banco.backend.models.entities.Cuenta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("CuentaRepository")
public interface CuentaRepository extends CrudRepository<Cuenta,Integer> {
}
