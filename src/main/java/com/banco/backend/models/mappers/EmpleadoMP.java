package com.banco.backend.models.mappers;

import com.banco.backend.models.dto.EmpleadoDTO;
import com.banco.backend.models.entities.Empleado;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpleadoMP {

    EmpleadoDTO mapEmpleado(Empleado empleado);
    Empleado mapEmpleado(EmpleadoDTO empleadoDTO);
    List<EmpleadoDTO> mapEmpleado(List<Empleado> empleados);

}
