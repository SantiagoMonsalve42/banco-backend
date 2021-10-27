package com.banco.backend.services;

import com.banco.backend.models.dao.SucursalDAO;
import com.banco.backend.models.entities.Sucursal;
import com.banco.backend.repos.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SucursalDAOImpl extends GenericDAOImpl<Sucursal, SucursalRepository> implements SucursalDAO {
    @Autowired
    public SucursalDAOImpl(SucursalRepository repository) {
        super(repository);
    }
}
