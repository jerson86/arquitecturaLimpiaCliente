package com.pragma.infrastructure.rest;

import com.pragma.application.dto.ClienteDTO;
import com.pragma.application.mapper.ClienteMapper;
import com.pragma.domain.port.inbound.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteRestController {
    private final ClienteService clienteService;
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    public ClienteRestController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @RequestMapping(value = "api/clientes", method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> getAllClientes(){
       return  ResponseEntity.ok().body(
                clienteMapper.mapToDTOList(
                        clienteService.getAllClientes()
                )

        );
    }

    @RequestMapping(value = "api/clientes/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClienteDTO> getCliente(@PathVariable Long id){
        return ResponseEntity.ok().body(
            clienteMapper.mapToDTO(
                    clienteService.getClienteById(id)
            )
        );
    }

    @RequestMapping(value = "api/clientes", method = RequestMethod.POST)
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO){
        return new ResponseEntity<>(
                clienteMapper.mapToDTO(
                    clienteService.createCliente(
                            clienteMapper.mapToModel(clienteDTO)
                    )
                ),
                HttpStatus.CREATED);
    }

    @RequestMapping(value = "api/clientes/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ClienteDTO> updateCliente(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id){
        return ResponseEntity.ok().body(
            clienteMapper.mapToDTO(
                clienteService.updateCliente(
                    clienteMapper.mapToModel(clienteDTO), id
                )
            )
        );
    }

    @RequestMapping(value = "api/clientes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ClienteDTO> deleteCliente(@PathVariable Long id){
        return ResponseEntity.ok().body(
                clienteMapper.mapToDTO(
                        clienteService.deleteCliente(id)
                )
        );
    }
}
