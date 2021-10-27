package com.banco.backend.models.mappers;

import com.banco.backend.models.dto.CiudadDTO;
import com.banco.backend.models.entities.Ciudad;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CiudadMP {
    CiudadDTO mapCiudad(Ciudad ciudad);
    Ciudad mapCiudad(CiudadDTO ciudadDTO);
    List<CiudadDTO> mapCiudad(List<Ciudad> ciudades);
}
