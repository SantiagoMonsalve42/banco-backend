package com.banco.backend.models.dao;

import com.banco.backend.models.entities.Cliente;
import com.banco.backend.models.entities.Empleado;
import com.banco.backend.models.entities.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaDAO extends GenericDAO<Persona> {
    Iterable<Empleado> readAllEmployees();
    Iterable<Cliente> readAllCostumers();
    Optional<Empleado> readByMailEmpleado(String mail);
    Optional<Cliente> readByMailCliente(String mail);
}
