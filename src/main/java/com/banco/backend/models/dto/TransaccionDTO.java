package com.banco.backend.models.dto;

import com.banco.backend.models.entities.Cuenta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionDTO {
    private Integer id;
    private String destino;
    private LocalDateTime fechaAlta;
    private LocalDateTime fechaModificacion;
    private Byte estado;
    private CuentaDTO cuenta;
    private BigDecimal saldo;
}
