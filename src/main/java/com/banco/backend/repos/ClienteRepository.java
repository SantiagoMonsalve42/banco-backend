package com.banco.backend.repos;

import com.banco.backend.models.entities.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("ClienteRepository")
public interface ClienteRepository extends PersonaRepository {
}
