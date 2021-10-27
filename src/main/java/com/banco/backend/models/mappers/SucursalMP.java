package com.banco.backend.models.mappers;

import com.banco.backend.models.dto.SucursalDTO;
import com.banco.backend.models.entities.Sucursal;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SucursalMP {
    SucursalDTO mapSucursal(Sucursal sucursal);
    Sucursal mapSucursal(SucursalDTO sucursalDTO);
    List<SucursalDTO> mapSucursal(List<Sucursal> sucursales);
}
