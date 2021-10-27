package com.banco.backend.models.mappers.config;

import com.banco.backend.models.dto.EmpleadoDTO;
import com.banco.backend.models.entities.Empleado;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;

@MapperConfig
public interface EmpleadoMPConfig extends PersonaMPConfig{
    @InheritConfiguration(name = "mapPersona")
    void mapEmpleado(Empleado empleado,@MappingTarget EmpleadoDTO empleadoDTO);
}
