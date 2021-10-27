package com.banco.backend.models.mappers.config;

import com.banco.backend.models.dto.PersonaDTO;
import com.banco.backend.models.entities.Persona;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;

@MapperConfig
public interface PersonaMPConfig {
    void mapPersona(Persona persona,@MappingTarget PersonaDTO personaDTO);
}
