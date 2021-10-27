package com.banco.backend.repos;

import com.banco.backend.models.entities.Departamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("DepartamentoRepository")
public interface DepartamentoRepository extends CrudRepository<Departamento,Integer> {
}
