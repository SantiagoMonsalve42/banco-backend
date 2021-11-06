package com.banco.backend.repos;

import com.banco.backend.models.entities.Cliente;
import com.banco.backend.models.entities.Empleado;
import com.banco.backend.models.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PersonaRepository extends CrudRepository<Persona,Integer> {
    @Query("select e from Empleado e")
    Iterable<Empleado> readAllEmployees();
    @Query("select c from Cliente c")
    Iterable<Cliente> readAllCostumers();
    @Query("select e from Empleado e where UPPER(e.email) = ?1")
    Optional<Empleado> readByMailEmpleado(String mail);
    @Query("select c from Cliente c where UPPER(c.email) = ?1")
    Optional<Cliente> readByMailCliente(String mail);
}
