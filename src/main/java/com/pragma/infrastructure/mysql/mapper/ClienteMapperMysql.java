package com.pragma.infrastructure.mysql.mapper;

import com.pragma.domain.model.Cliente;
import com.pragma.infrastructure.mysql.entity.ClienteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapperMysql {
    @Autowired
    private ModelMapper modelMapper;

    // convert DTO to Entity
    public ClienteEntity mapToEntity(Cliente cliente){
        return modelMapper.map(cliente,ClienteEntity.class);
    }
    // convert entity to Model
    public Cliente mapToModel(ClienteEntity clienteEntity){
        return modelMapper.map(clienteEntity, Cliente.class);
    }
}
