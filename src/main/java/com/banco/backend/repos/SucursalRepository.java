package com.banco.backend.repos;

import com.banco.backend.models.entities.Empleado;
import com.banco.backend.models.entities.Persona;
import com.banco.backend.models.entities.Sucursal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("SucursalRepository")
public interface SucursalRepository extends CrudRepository<Sucursal,Integer> {
    @Query("select e from Empleado e where e.sucursal.id = ?1 ")
    Iterable<Empleado> readAllByIdSucursal(Integer id);
}
