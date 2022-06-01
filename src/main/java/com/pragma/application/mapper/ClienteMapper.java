package com.pragma.application.mapper;

import com.pragma.application.dto.ClienteDTO;
import com.pragma.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClienteMapper {
    @Autowired
    private ModelMapper modelMapper;

    // convert DTO to Entity
    public Cliente mapToModel(ClienteDTO clienteDTO){

        return modelMapper.map(clienteDTO,Cliente.class);
    }
    // convert entity to DTO
    public ClienteDTO mapToDTO(Cliente cliente){

        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public List<Cliente> mapToModelList(List<ClienteDTO> clientesDTO){
        List<Cliente> clientes= new ArrayList<>();
        clientesDTO.forEach(cliente -> {
            Cliente clienteResponse = this.mapToModel(cliente);
            clientes.add(clienteResponse);
        });
        return clientes;
    }

    public List<ClienteDTO> mapToDTOList(List<Cliente> clientes){

        List<ClienteDTO> clientesDTO = new ArrayList<>();
        clientes.forEach(cliente -> {
            ClienteDTO clienteResponse = this.mapToDTO(cliente);
            clientesDTO.add(clienteResponse);
        });
        return clientesDTO;
    }
}
