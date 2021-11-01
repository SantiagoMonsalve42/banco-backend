package com.banco.backend.models.dao;

import com.banco.backend.models.entities.Cliente;
import com.banco.backend.models.entities.Empleado;
import com.banco.backend.models.entities.Persona;

import java.util.List;

public interface PersonaDAO extends GenericDAO<Persona> {
    Iterable<Empleado> readAllEmployees();
    Iterable<Cliente> readAllCostumers();
}
