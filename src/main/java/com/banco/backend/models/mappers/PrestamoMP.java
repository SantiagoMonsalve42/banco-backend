package com.banco.backend.models.mappers;

import com.banco.backend.models.dto.PrestamoDTO;
import com.banco.backend.models.entities.Prestamo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrestamoMP {
    Prestamo mapPrestamo(PrestamoDTO prestamoDTO);
    PrestamoDTO mapPrestamo(Prestamo prestamo);
    List<PrestamoDTO> mapPrestamo(List<Prestamo> prestamos);
}
