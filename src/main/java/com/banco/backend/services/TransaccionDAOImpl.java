package com.banco.backend.services;

import com.banco.backend.models.dao.TransaccionDAO;
import com.banco.backend.models.entities.Transaccion;
import com.banco.backend.repos.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransaccionDAOImpl extends GenericDAOImpl<Transaccion, TransaccionRepository> implements TransaccionDAO {
    @Autowired
    public TransaccionDAOImpl(TransaccionRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Transaccion> readByClienteId(Integer id) {
        return repository.readByClienteId(id);
    }
}
