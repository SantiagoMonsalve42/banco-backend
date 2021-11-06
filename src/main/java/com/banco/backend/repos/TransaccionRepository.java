package com.banco.backend.repos;

import com.banco.backend.models.entities.Transaccion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("TransaccionRepository")
public interface TransaccionRepository extends CrudRepository<Transaccion,Integer> {
    @Query("select t from Transaccion t where t.cuenta.cliente.id = ?1")
    Iterable<Transaccion> readByClienteId(Integer id);
}
