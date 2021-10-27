package com.banco.backend.models.dto;

import com.banco.backend.models.entities.Sucursal;
import com.banco.backend.models.enums.TipoEmpleado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO extends PersonaDTO{
    private BigDecimal sueldo;
    private TipoEmpleado tipo_empleado;
    private Sucursal sucursal;
}
