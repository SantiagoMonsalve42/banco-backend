package com.banco.backend.services;

import com.banco.backend.models.dao.TelefonoDAO;
import com.banco.backend.models.entities.Telefono;
import com.banco.backend.repos.TelefonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelefonoDAOImpl extends GenericDAOImpl<Telefono, TelefonoRepository> implements TelefonoDAO {
    @Autowired
    public TelefonoDAOImpl(TelefonoRepository repository) {
        super(repository);
    }
}
