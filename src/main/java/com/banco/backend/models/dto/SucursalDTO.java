package com.banco.backend.models.dto;

import com.banco.backend.models.entities.Ciudad;
import com.banco.backend.models.entities.Direccion;
import com.banco.backend.models.entities.Empleado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SucursalDTO {
    private Integer id;
    private String nombre;
    private Direccion direccion;
    private Ciudad ciudad;
}
