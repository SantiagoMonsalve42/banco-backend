package com.banco.backend.models.mappers;

import com.banco.backend.models.dto.CuentaDTO;
import com.banco.backend.models.entities.Cuenta;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CuentaMP {
    Cuenta mapCuenta(CuentaDTO cuentaDTO);
    CuentaDTO mapCuenta(Cuenta cuenta);
    List<CuentaDTO> mapCuenta(List<Cuenta> cuentas);
}
