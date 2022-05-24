package com.pragma.cliente.service;

import com.pragma.cliente.dto.ClienteDTO;
import com.pragma.cliente.entity.Cliente;
import com.pragma.cliente.exception.ResourceNotFoundException;
import com.pragma.cliente.mappers.ClienteMapper;
import com.pragma.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteMapper clienteMapper;
    @Override
    public List<ClienteDTO> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        clientes.stream().findFirst().orElseThrow(()->
                new ResourceNotFoundException("cliente",1L)
        );
        // convert entity to DTO
        List<ClienteDTO> clienteDTOS= new ArrayList<>();
        clientes.forEach(cliente -> {
            ClienteDTO clienteResponse = clienteMapper.mapToDTO(cliente);
            clienteDTOS.add(clienteResponse);
        });

        return clienteDTOS;
    }

    @Override
    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        // convert DTO to Entity
        Cliente clienteRequest = clienteMapper.mapToEntity(clienteDTO);
        clienteRepository.save(clienteRequest);
        // convert entity to DTO
        ClienteDTO clienteResponse = clienteMapper.mapToDTO(clienteRequest);
        return clienteResponse;
    }

    @Override
    public ClienteDTO updateCliente(long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("cliente",id));
        // convert DTO to Entity
        Cliente clienteRequest = clienteMapper.mapToEntity(clienteDTO);
        cliente.setId(id);
        cliente.setNombres(clienteRequest.getNombres());
        cliente.setApellidos(clienteRequest.getApellidos());
        cliente.setEdad(clienteRequest.getEdad());
        cliente.setCiudadNacimiento(clienteRequest.getCiudadNacimiento());
        clienteRepository.save(cliente);
        // convert entity to DTO
        ClienteDTO clienteResponse = clienteMapper.mapToDTO(cliente);
        return clienteResponse;
    }

    @Override
    public ClienteDTO deleteCliente(long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("cliente",id));
        clienteRepository.delete(cliente);
        // convert entity to DTO
        ClienteDTO clienteResponse = clienteMapper.mapToDTO(cliente);
        return clienteResponse;
    }

    @Override
    public ClienteDTO getClienteById(long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("cliente",id));
        // convert entity to DTO
        ClienteDTO clienteResponse = clienteMapper.mapToDTO(cliente);
        return clienteResponse;
    }

    @Override
    public ClienteDTO findByTipoDocumentoAndDocumento(String tipoDocumento, String documento) {
        Cliente cliente = clienteRepository.findByTipoDocumentoAndDocumento(tipoDocumento, documento).orElseThrow(()-> new ResourceNotFoundException("cliente",1L));
        // convert entity to DTO
        ClienteDTO clienteResponse = clienteMapper.mapToDTO(cliente);
        return clienteResponse;
    }
}
