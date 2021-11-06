package com.banco.backend.models.dao;

import com.banco.backend.models.entities.Cuenta;

import java.util.Optional;

public interface CuentaDAO extends GenericDAO<Cuenta> {
    Iterable<Cuenta> getCuentasByIdPersona(Integer id);
}
