package com.banco.backend.models.dao;

import com.banco.backend.models.entities.Ciudad;

public interface CiudadDAO extends GenericDAO<Ciudad>{
    Iterable<Ciudad> obtenerPorIdDepartamento(Integer id);
}
