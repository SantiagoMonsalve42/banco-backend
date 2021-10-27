package com.banco.backend.services;

import com.banco.backend.models.dao.DireccionDAO;
import com.banco.backend.models.entities.Direccion;
import com.banco.backend.repos.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DireccionDAOImpl extends GenericDAOImpl<Direccion, DireccionRepository> implements DireccionDAO {
    @Autowired
    public DireccionDAOImpl(DireccionRepository repository) {
        super(repository);
    }
}
