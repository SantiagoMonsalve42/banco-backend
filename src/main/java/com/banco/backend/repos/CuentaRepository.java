package com.banco.backend.repos;

import com.banco.backend.models.entities.Cuenta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("CuentaRepository")
public interface CuentaRepository extends CrudRepository<Cuenta,Integer> {
    @Query("select c from Cuenta c where c.cliente.id = ?1")
    Iterable<Cuenta> getCuentasByIdPersona(Integer id);
}
