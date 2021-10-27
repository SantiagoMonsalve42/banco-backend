package com.banco.backend.models.dto;

import com.banco.backend.models.entities.Cuenta;
import com.banco.backend.models.enums.TipoCliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO extends PersonaDTO{
    private TipoCliente tipo_cliente;
}
