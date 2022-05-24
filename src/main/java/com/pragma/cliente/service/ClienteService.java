package com.pragma.cliente.service;

import com.pragma.cliente.dto.ClienteDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClienteService {
    List<ClienteDTO> getAllClientes();

    ClienteDTO createCliente(ClienteDTO clienteDTO);

    ClienteDTO updateCliente(long id, ClienteDTO clienteDTO);

    ClienteDTO deleteCliente(long id);

    ClienteDTO getClienteById(long id);

    ClienteDTO findByTipoDocumentoAndDocumento(String tipoDocumento, String documento);
}
