package com.banco.backend.repos;

import com.banco.backend.models.entities.Telefono;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("TelefonoRepository")
public interface TelefonoRepository extends CrudRepository<Telefono,Integer> {
}
