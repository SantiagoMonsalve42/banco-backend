package com.banco.backend.models.dto;

import com.banco.backend.models.entities.Cliente;
import com.banco.backend.models.entities.Prestamo;
import com.banco.backend.models.enums.TipoCuenta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDTO {

    private Integer id;
    private TipoCuenta tipo_cuenta;
    private BigDecimal saldo;
    private ClienteDTO cliente;

}
