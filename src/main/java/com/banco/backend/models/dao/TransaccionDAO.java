package com.banco.backend.models.dao;

import com.banco.backend.models.entities.Transaccion;

import java.util.Optional;

public interface TransaccionDAO extends GenericDAO<Transaccion> {

    Iterable<Transaccion> readByClienteId(Integer id);
}
