package com.banco.backend.models.mappers;

import com.banco.backend.models.dto.TelefonoDTO;
import com.banco.backend.models.entities.Telefono;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TelefonoMP {
    Telefono mapTelefono(TelefonoDTO telefonoDTO);
    TelefonoDTO mapTelefono(Telefono telefono);
    List<TelefonoDTO> mapTelefono(List<Telefono> telefonos);
}
