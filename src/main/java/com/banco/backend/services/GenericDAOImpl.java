package com.banco.backend.services;

import com.banco.backend.models.dao.GenericDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class GenericDAOImpl <E,R extends CrudRepository<E,Integer>> implements GenericDAO<E> {

    protected final R repository;

    public GenericDAOImpl(R repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> readAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> readById(Integer id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
