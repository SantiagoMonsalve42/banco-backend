package com.banco.backend.services;

import com.banco.backend.models.dao.EmpleadoDAO;
import com.banco.backend.models.entities.Empleado;
import com.banco.backend.models.entities.Persona;
import com.banco.backend.repos.EmpleadoRepository;
import com.banco.backend.repos.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpleadoDAO {
    @Autowired
    public EmpleadoDAOImpl(@Qualifier("EmpleadoRepository") PersonaRepository repository) {
        super(repository);
    }
    
}
