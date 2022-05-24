package com.pragma.cliente.mappers;

import com.pragma.cliente.dto.ClienteDTO;
import com.pragma.cliente.entity.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper{
    @Autowired
    private ModelMapper modelMapper;

    // convert DTO to Entity
    public Cliente mapToEntity(ClienteDTO clienteDTO){
        return modelMapper.map(clienteDTO,Cliente.class);
    }
    // convert entity to DTO
    public ClienteDTO mapToDTO(Cliente cliente){
        return modelMapper.map(cliente, ClienteDTO.class);
    }
}
