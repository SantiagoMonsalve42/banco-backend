package com.banco.backend.models.dto;

import com.banco.backend.models.entities.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelefonoDTO {
    private Integer id;
    private BigInteger telefono;
    private LocalDateTime fechaAlta;
    private LocalDateTime fechaModificacion;
}
