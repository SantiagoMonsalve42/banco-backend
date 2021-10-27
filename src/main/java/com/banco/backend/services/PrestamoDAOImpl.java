package com.banco.backend.services;

import com.banco.backend.models.dao.PrestamoDAO;
import com.banco.backend.models.entities.Prestamo;
import com.banco.backend.repos.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestamoDAOImpl extends GenericDAOImpl<Prestamo, PrestamoRepository> implements PrestamoDAO {
    @Autowired
    public PrestamoDAOImpl(PrestamoRepository repository) {
        super(repository);
    }
}
