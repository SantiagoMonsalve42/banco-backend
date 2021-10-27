package com.banco.backend.models.dto;

import com.banco.backend.models.entities.Persona;
import com.banco.backend.models.entities.Sucursal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDTO {

    private Integer id;
    private String calle;
    private String numero;
    private String codigo_postal;
    private String piso;
    private String localidad;
    private Persona persona;
    private Sucursal sucursal;

}
