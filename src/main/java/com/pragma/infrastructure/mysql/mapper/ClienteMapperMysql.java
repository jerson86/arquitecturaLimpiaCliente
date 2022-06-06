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
        try{
            return modelMapper.map(cliente,ClienteEntity.class);
        }catch (Exception e){
            System.err.println(e);
            e.toString();
        }
        return null;
    }
    // convert entity to Model
    public Cliente mapToModel(ClienteEntity clienteEntity){
        try{
            modelMapper.map(clienteEntity, Cliente.class);
        }catch (Exception e){
            System.err.println(e);
            e.toString();
        }
        return null;
    }
}
