package com.banco.backend.models.dto;

import com.banco.backend.models.entities.Cuenta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoDTO {
    private Integer id;
    private BigDecimal saldo;
    private Byte estado;
    private LocalDateTime fechaAlta;
    private LocalDateTime fechaModificacion;
    private CuentaDTO cuenta;
}
