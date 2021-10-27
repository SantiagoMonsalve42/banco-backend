package com.banco.backend.models.dao;

import java.util.List;

public interface GenericDAO <E>{
    List<E> readAll();
    E readById(Integer id);
    E create(E entity);
    void deleteById(Integer id);
}
