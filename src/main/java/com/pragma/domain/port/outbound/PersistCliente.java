package com.pragma.domain.port.outbound;

import com.pragma.domain.model.Cliente;

import java.util.List;

public interface PersistCliente {
    Cliente save(Cliente cliente);
    Cliente update(Cliente cliente, Long id);
    Cliente delete(long id);
    Cliente getById(long id);
    List<Cliente> getAll();
    Cliente getByTipoDocumentoAndDocumento(String tipoDocumento, String documento);

}
