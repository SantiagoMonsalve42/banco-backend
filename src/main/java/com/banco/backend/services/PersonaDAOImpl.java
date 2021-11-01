package com.banco.backend.services;

import com.banco.backend.models.dao.PersonaDAO;
import com.banco.backend.models.entities.Cliente;
import com.banco.backend.models.entities.Empleado;
import com.banco.backend.models.entities.Persona;
import com.banco.backend.repos.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonaDAOImpl extends GenericDAOImpl<Persona, PersonaRepository> implements PersonaDAO {
    public PersonaDAOImpl(PersonaRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Empleado> readAllEmployees() {
        return repository.readAllEmployees();
    }

    @Override
    public Iterable<Cliente> readAllCostumers() {
        return repository.readAllCostumers();
    }
}
