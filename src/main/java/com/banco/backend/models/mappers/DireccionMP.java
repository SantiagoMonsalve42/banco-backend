package com.banco.backend.models.mappers;

import com.banco.backend.models.dto.DireccionDTO;
import com.banco.backend.models.entities.Direccion;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DireccionMP {
    Direccion mapDireccion(DireccionDTO direccionDTO);
    DireccionDTO mapDireccion(Direccion direccion);
    List<DireccionDTO> mapDireccion(List<Direccion> direcciones);
}
