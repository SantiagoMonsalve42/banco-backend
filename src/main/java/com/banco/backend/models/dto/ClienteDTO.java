package com.banco.backend.models.dto;

import com.banco.backend.models.entities.Cuenta;
import com.banco.backend.models.entities.Direccion;
import com.banco.backend.models.enums.TipoCliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO extends PersonaDTO{
    private TipoCliente tipo_cliente;

    public ClienteDTO(Integer id, String primer_nombre, String segundo_nombre, String primer_apellido, String segundo_apellido, String email, LocalDateTime fechaAlta, LocalDateTime fechaModificacion, Direccion direccion, TipoCliente tipo_cliente) {
        super(id, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, email,  fechaAlta, fechaModificacion, direccion);
        this.tipo_cliente = tipo_cliente;
    }
}
