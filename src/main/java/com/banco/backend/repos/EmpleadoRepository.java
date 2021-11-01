package com.banco.backend.repos;

import com.banco.backend.models.entities.Empleado;
import com.banco.backend.models.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("EmpleadoRepository")
public interface EmpleadoRepository extends PersonaRepository{
}
