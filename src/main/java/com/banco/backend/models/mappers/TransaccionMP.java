package com.banco.backend.models.mappers;

import com.banco.backend.models.dto.TransaccionDTO;
import com.banco.backend.models.entities.Transaccion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransaccionMP {
    TransaccionDTO mapTransaccion(Transaccion transaccion);
    Transaccion mapTransaccion(TransaccionDTO transaccionDTO);
    List<TransaccionDTO> mapTransaccion(List<Transaccion> transacciones);
}
