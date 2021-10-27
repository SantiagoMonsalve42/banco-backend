package com.banco.backend.services;

import com.banco.backend.models.dao.CuentaDAO;
import com.banco.backend.models.entities.Cuenta;
import com.banco.backend.repos.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaDAOImpl extends GenericDAOImpl<Cuenta, CuentaRepository> implements CuentaDAO {
    @Autowired
    public CuentaDAOImpl(CuentaRepository repository) {
        super(repository);
    }
}
