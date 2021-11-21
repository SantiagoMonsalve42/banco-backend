package com.banco.backend.models.dto;

import com.banco.backend.models.entities.Direccion;
import com.banco.backend.models.entities.Sucursal;
import com.banco.backend.models.entities.Telefono;
import com.banco.backend.models.enums.TipoEmpleado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO extends PersonaDTO{
    private BigDecimal sueldo;
    private TipoEmpleado tipo_empleado;

    public EmpleadoDTO(Integer id, String primer_nombre, String segundo_nombre, String primer_apellido, String segundo_apellido, String email, LocalDateTime fechaAlta, LocalDateTime fechaModificacion, Direccion direccion, Telefono telefono, BigDecimal sueldo, TipoEmpleado tipo_empleado) {
        super(id, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, email, fechaAlta, fechaModificacion, direccion, telefono);
        this.sueldo = sueldo;
        this.tipo_empleado = tipo_empleado;
    }

}
