package com.banco.backend.models.dto;

import com.banco.backend.models.entities.Departamento;
import com.banco.backend.models.entities.Sucursal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CiudadDTO {

    private Integer id;
    private String nombre;
    private Departamento departamento;

}
