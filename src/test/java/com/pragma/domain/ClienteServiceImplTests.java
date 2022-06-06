package com.pragma.domain;

import com.pragma.domain.model.Cliente;
import com.pragma.domain.port.inbound.ClienteService;
import com.pragma.domain.port.outbound.PersistCliente;
import com.pragma.domain.service.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceImplTests {

    private Cliente cliente;
    @Mock
    private PersistCliente persistCliente;
    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteServiceImpl clienteServiceImpl;



    @BeforeEach
    void setUp()
    {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombres("Pepito");
        cliente.setApellidos("Perez");
        cliente.setTipoDocumento("CC");
        cliente.setDocumento("123456");
        cliente.setEdad((short) 18);
        cliente.setCiudadNacimiento("Ocana");
    }

    @Test
    void givenSaveClienteWhenIsNotNull(){
        lenient().when(clienteServiceImpl.createCliente(cliente)).thenReturn(cliente);
        //verify(clienteServiceImpl).createCliente(cliente);
    }

    @Test
    void givenUpdateClienteWhenIsNotNull(){
        lenient().when(clienteServiceImpl.updateCliente(cliente,1L)).thenReturn(cliente);
    }

    @Test
    void givenDeleteClienteWhenIsNotNull(){
        lenient().when(clienteServiceImpl.deleteCliente(1L)).thenReturn(cliente);
    }
    @Test
    void givenGetByIdClienteWhenIsNotNull(){
        lenient().when(clienteServiceImpl.getClienteById(1L)).thenReturn(cliente);
    }
    @Test
    void givenGetAllClientesWhenIsNotNull(){
        lenient().when(clienteServiceImpl.getAllClientes()).thenReturn(List.of(cliente));
    }
    @Test
    void givenGetByTipeDocumentAndDocumentToClienteWhenIsNotNull(){
        lenient().when(clienteServiceImpl.getByTipoDocumentoAndDocumento("CC","123456")).thenReturn(cliente);
    }
}
