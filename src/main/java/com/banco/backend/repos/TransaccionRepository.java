package com.banco.backend.repos;

import com.banco.backend.models.entities.Transaccion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("TransaccionRepository")
public interface TransaccionRepository extends CrudRepository<Transaccion,Integer> {
}
