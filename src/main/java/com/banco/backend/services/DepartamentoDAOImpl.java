package com.banco.backend.services;

import com.banco.backend.models.dao.DepartamentoDAO;
import com.banco.backend.models.entities.Departamento;
import com.banco.backend.repos.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoDAOImpl extends GenericDAOImpl<Departamento, DepartamentoRepository> implements DepartamentoDAO {
    @Autowired
    public DepartamentoDAOImpl(DepartamentoRepository repository) {
        super(repository);
    }
}
