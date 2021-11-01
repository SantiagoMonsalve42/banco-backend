package com.banco.backend.models.dto;

import com.banco.backend.models.entities.Direccion;
import com.banco.backend.models.entities.Telefono;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class PersonaDTO {
    private Integer id;
    private String primer_nombre;
    private String segundo_nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private String email;
    private LocalDateTime fechaAlta;
    private LocalDateTime fechaModificacion;
    private Direccion direccion;
}
