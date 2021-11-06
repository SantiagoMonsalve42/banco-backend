package com.banco.backend.models.dao;

import com.banco.backend.models.entities.Prestamo;

public interface PrestamoDAO extends GenericDAO<Prestamo> {
    Iterable<Prestamo> readByClientId(Integer id);
}
