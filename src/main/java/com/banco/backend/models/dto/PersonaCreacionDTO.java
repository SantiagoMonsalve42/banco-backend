package com.banco.backend.models.dto;

import com.banco.backend.models.entities.Direccion;
import com.banco.backend.models.entities.Telefono;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaCreacionDTO {
        private String primer_nombre;
        private String segundo_nombre;
        private String primer_apellido;
        private String segundo_apellido;
        private String email;
        private String password;
        private Direccion direccion;
        private Telefono telefono;
}
