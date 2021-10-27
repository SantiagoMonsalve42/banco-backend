package com.banco.backend.repos;

import com.banco.backend.models.entities.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersonaRepository extends CrudRepository<Persona,Integer> {

}
