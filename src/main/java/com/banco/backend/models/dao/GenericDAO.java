package com.banco.backend.models.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO <E>{
    Iterable<E> readAll();
    Optional<E> readById(Integer id);
    Optional<E> create(E entity);
    void deleteById(Integer id);
}
