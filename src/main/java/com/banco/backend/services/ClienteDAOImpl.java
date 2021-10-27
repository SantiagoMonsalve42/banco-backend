package com.banco.backend.services;

import com.banco.backend.models.dao.ClienteDAO;
import com.banco.backend.repos.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ClienteDAOImpl extends PersonaDAOImpl implements ClienteDAO {
    @Autowired
    public ClienteDAOImpl(@Qualifier("ClienteRepository") PersonaRepository repository) {
        super(repository);
    }
}
