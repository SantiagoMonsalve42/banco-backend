package com.banco.backend.models.dao;

import com.banco.backend.models.entities.Empleado;
import com.banco.backend.models.entities.Persona;
import com.banco.backend.models.entities.Sucursal;

public interface SucursalDAO extends GenericDAO<Sucursal> {
    Iterable<Empleado> readAllByIdSucursal(Integer id);
}
