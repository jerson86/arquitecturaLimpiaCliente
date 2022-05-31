package com.pragma.domain;

import java.util.List;

public interface ClienteRepository {
    List<Cliente> getAllClientes();

    Cliente createCliente(Cliente cliente);

    Cliente updateCliente(long id, Cliente cliente);

    Cliente deleteCliente(long id);

    Cliente getClienteById(long id);

    Cliente findByTipoDocumentoAndDocumento(String tipoDocumento, String documento);
}
