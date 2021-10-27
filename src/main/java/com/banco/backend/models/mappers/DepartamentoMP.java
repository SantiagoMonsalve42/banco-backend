package com.banco.backend.models.mappers;

import com.banco.backend.models.dto.DepartamentoDTO;
import com.banco.backend.models.entities.Departamento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartamentoMP {
    Departamento mapDepartamento(DepartamentoDTO departamentoDTO);
    DepartamentoDTO mapDepartamento(Departamento departamento);
    List<DepartamentoDTO> mapDepartamento(List<Departamento> departamentos);
}
