package com.banco.backend.models.mappers.config;

import com.banco.backend.models.dto.ClienteDTO;
import com.banco.backend.models.entities.Cliente;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;

@MapperConfig
public interface ClienteMPConfig extends PersonaMPConfig{

    @InheritConfiguration(name = "mapPersona")
    void mapCliente(Cliente cliente,@MappingTarget ClienteDTO clienteDTO);

}
