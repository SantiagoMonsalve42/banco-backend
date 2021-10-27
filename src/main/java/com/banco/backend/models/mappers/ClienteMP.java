package com.banco.backend.models.mappers;

import com.banco.backend.models.dto.ClienteDTO;
import com.banco.backend.models.entities.Cliente;
import com.banco.backend.models.mappers.config.ClienteMPConfig;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",config = ClienteMPConfig.class)
public interface ClienteMP {
    ClienteDTO mapCliente(Cliente cliente);
    Cliente mapCliente(ClienteDTO clienteDTO);
    List<ClienteDTO> mapCliente(List<Cliente> clientes);
}
