package com.pragma.cliente.controller;

import com.pragma.cliente.dto.ClienteDTO;
import com.pragma.cliente.service.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {
    @Autowired
    ClienteServiceImpl clienteService = new ClienteServiceImpl();

    @RequestMapping(value = "api/clientes", method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> getAllClientes(){
        return  ResponseEntity.ok().body(clienteService.getAllClientes());
    }

    @RequestMapping(value = "api/clientes/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClienteDTO> getCliente(@PathVariable Long id){
        return ResponseEntity.ok().body(clienteService.getClienteById(id));
    }

    @RequestMapping(value = "api/clientes", method = RequestMethod.POST)
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO){
        return ResponseEntity.ok().body(clienteService.createCliente(clienteDTO));
    }

    @RequestMapping(value = "api/clientes/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ClienteDTO> updateCliente(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id){
        return ResponseEntity.ok().body(clienteService.updateCliente(id,clienteDTO));
    }

    @RequestMapping(value = "api/clientes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ClienteDTO> deleteCliente(@PathVariable Long id){
        return ResponseEntity.ok().body(clienteService.deleteCliente(id));
    }

}
