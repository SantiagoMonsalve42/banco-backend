package com.banco.backend.services;

import com.banco.backend.models.dao.SucursalDAO;
import com.banco.backend.models.entities.Empleado;
import com.banco.backend.models.entities.Persona;
import com.banco.backend.models.entities.Sucursal;
import com.banco.backend.repos.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SucursalDAOImpl extends GenericDAOImpl<Sucursal, SucursalRepository> implements SucursalDAO {
    @Autowired
    public SucursalDAOImpl(SucursalRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Empleado> readAllByIdSucursal(Integer id) {
        return repository.readAllByIdSucursal(id);
    }
}
