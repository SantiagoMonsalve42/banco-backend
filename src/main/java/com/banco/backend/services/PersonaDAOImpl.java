package com.banco.backend.services;

import com.banco.backend.models.dao.PersonaDAO;
import com.banco.backend.models.entities.Cliente;
import com.banco.backend.models.entities.Empleado;
import com.banco.backend.models.entities.Persona;
import com.banco.backend.repos.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;
import java.util.Optional;

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

    @Override
    public Optional<Empleado> readByMailEmpleado(String mail) {
        return repository.readByMailEmpleado(mail.toUpperCase());
    }

    @Override
    public Optional<Cliente> readByMailCliente(String mail) {
        return repository.readByMailCliente(mail.toUpperCase());
    }


}
