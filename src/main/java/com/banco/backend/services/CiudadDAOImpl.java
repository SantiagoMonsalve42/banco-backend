package com.banco.backend.services;

import com.banco.backend.models.dao.CiudadDAO;
import com.banco.backend.models.entities.Ciudad;
import com.banco.backend.repos.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CiudadDAOImpl extends GenericDAOImpl<Ciudad, CiudadRepository> implements CiudadDAO {

    @Autowired
    public CiudadDAOImpl(CiudadRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Ciudad> obtenerPorIdDepartamento(Integer id) {
        return repository.obtenerPorIdDepartamento(id);
    }
}
