package com.pragma.infrastructure.mysql.adapter;

import com.pragma.domain.exception.ResourceNotFoundException;
import com.pragma.domain.model.Cliente;
import com.pragma.domain.port.outbound.PersistCliente;
import com.pragma.infrastructure.mysql.entity.ClienteEntity;
import com.pragma.infrastructure.mysql.mapper.ClienteMapperMysql;
import com.pragma.infrastructure.mysql.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClienteAdapter implements PersistCliente {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteMapperMysql clienteMapperMysql;

    public ClienteAdapter(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> getAll() {
        List<ClienteEntity> clientesEnt = clienteRepository.findAll();
        clientesEnt.stream().findFirst().orElseThrow(()->
                new ResourceNotFoundException("cliente",1L)
        );
        // convert entity to DTO
        List<Cliente> clientes= new ArrayList<>();
        clientesEnt.forEach(cliente -> {
            Cliente clienteResponse = clienteMapperMysql.mapToModel(cliente);
            clientes.add(clienteResponse);
        });

        return clientes;
    }

    @Override
    public Cliente save(Cliente cliente) {
        // convert DTO to Entity
        ClienteEntity clienteRequest = clienteMapperMysql.mapToEntity(cliente);
        clienteRepository.save(clienteRequest);
        // convert entity to DTO
        Cliente clienteResponse = clienteMapperMysql.mapToModel(clienteRequest);
        return clienteResponse;
    }

    @Override
    public Cliente update(Cliente cliente, Long id) {
        ClienteEntity clienteEntity = clienteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("cliente",id));
        // convert DTO to Entity
        ClienteEntity clienteRequest = clienteMapperMysql.mapToEntity(cliente);
        clienteEntity.setId(id);
        clienteEntity.setNombres(clienteRequest.getNombres());
        clienteEntity.setApellidos(clienteRequest.getApellidos());
        clienteEntity.setEdad(clienteRequest.getEdad());
        clienteEntity.setCiudadNacimiento(clienteRequest.getCiudadNacimiento());
        clienteRepository.save(clienteEntity);
        // convert entity to DTO
        Cliente clienteResponse = clienteMapperMysql.mapToModel(clienteEntity);
        return clienteResponse;
    }

    @Override
    public Cliente delete(long id) {
        ClienteEntity clienteEntity = clienteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("cliente",id));
        clienteRepository.delete(clienteEntity);
        // convert entity to DTO
        Cliente clienteResponse = clienteMapperMysql.mapToModel(clienteEntity);
        return clienteResponse;
    }

    @Override
    public Cliente getById(long id) {
        ClienteEntity clienteEntity = clienteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("cliente",id));
        // convert entity to DTO
        Cliente clienteResponse = clienteMapperMysql.mapToModel(clienteEntity);
        return clienteResponse;
    }

    @Override
    public Cliente getByTipoDocumentoAndDocumento(String tipoDocumento, String documento) {
        ClienteEntity clienteEntity = clienteRepository.findByTipoDocumentoAndDocumento(tipoDocumento, documento).orElseThrow(()-> new ResourceNotFoundException("cliente",1L));
        // convert entity to DTO
        Cliente clienteResponse = clienteMapperMysql.mapToModel(clienteEntity);
        return clienteResponse;
    }
}
