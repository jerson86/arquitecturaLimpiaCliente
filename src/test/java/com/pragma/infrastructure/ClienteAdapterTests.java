package com.pragma.infrastructure;

import com.pragma.domain.model.Cliente;
import com.pragma.infrastructure.mysql.adapter.ClienteAdapter;
import com.pragma.infrastructure.mysql.entity.ClienteEntity;
import com.pragma.infrastructure.mysql.mapper.ClienteMapperMysql;
import com.pragma.infrastructure.mysql.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ClienteAdapterTests {

    private Cliente cliente;

    private ClienteEntity clienteEntity;
    @Mock
    private ClienteMapperMysql clienteMapperMysql;

    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private ClienteAdapter clienteAdapter= new ClienteAdapter(clienteRepository);

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

        clienteEntity = new ClienteEntity();
        clienteEntity.setId(1L);
        clienteEntity.setNombres("Pepito");
        clienteEntity.setApellidos("Perez");
        clienteEntity.setTipoDocumento("CC");
        clienteEntity.setDocumento("123456");
        clienteEntity.setEdad((short) 18);
        clienteEntity.setCiudadNacimiento("Ocana");


    }

    @Test
    public void givenSaveClienteWhenIsNotNull(){
        lenient().when(clienteMapperMysql.mapToEntity(cliente)).thenReturn(clienteEntity);
        lenient().when(clienteRepository.save(clienteEntity)).thenReturn(clienteEntity);
        lenient().when(clienteMapperMysql.mapToModel(clienteEntity)).thenReturn(cliente);

        assertEquals(cliente, clienteAdapter.save(cliente));

        verify(clienteMapperMysql).mapToEntity(cliente);
        verify(clienteMapperMysql).mapToModel(clienteEntity);
        verify(clienteRepository).save(clienteEntity);

    }

    @Test
    void givenUpdateClienteWhenIsNotNull(){
        lenient().when(clienteMapperMysql.mapToEntity(cliente)).thenReturn(clienteEntity);
        lenient().when(clienteRepository.save(clienteEntity)).thenReturn(clienteEntity);
        lenient().when(clienteRepository.findById(1L)).thenReturn(Optional.ofNullable(clienteEntity));
        lenient().when(clienteMapperMysql.mapToModel(clienteEntity)).thenReturn(cliente);


        Assertions.assertEquals(cliente, clienteAdapter.update(cliente,1L));

        verify(clienteMapperMysql).mapToEntity(cliente);
        verify(clienteMapperMysql).mapToModel(clienteEntity);
        verify(clienteRepository).findById(1L);
        verify(clienteRepository).save(clienteEntity);
    }

    @Test
    void givenDeleteClienteWhenIsNotNull(){
        lenient().when(clienteRepository.findById(1L)).thenReturn(Optional.ofNullable(clienteEntity));
        lenient().when(clienteMapperMysql.mapToModel(clienteEntity)).thenReturn(cliente);

        Assertions.assertEquals(cliente, clienteAdapter.delete(1L));

        verify(clienteMapperMysql).mapToModel(clienteEntity);
        verify(clienteRepository).findById(1L);
        verify(clienteRepository).delete(clienteEntity);
    }
   @Test
    void givenGetByIdClienteWhenIsNotNull(){
        lenient().when(clienteRepository.findById(1L)).thenReturn(Optional.ofNullable(clienteEntity));
        lenient().when(clienteMapperMysql.mapToModel(clienteEntity)).thenReturn(cliente);

        Assertions.assertEquals(cliente, clienteAdapter.getById(1L));

       verify(clienteMapperMysql).mapToModel(clienteEntity);
        verify(clienteRepository).findById(1L);
    }
     @Test
    void givenGetAllClientesWhenIsNotNull(){
        lenient().when(clienteMapperMysql.mapToModel(clienteEntity)).thenReturn(cliente);
        lenient().when(clienteRepository.findAll()).thenReturn(List.of(clienteEntity));

        Assertions.assertEquals(List.of(cliente), clienteAdapter.getAll());

        verify(clienteMapperMysql).mapToModel(clienteEntity);
        verify(clienteRepository).findAll();
    }
    @Test
    void givenGetByTipeDocumentAndDocumentToClienteWhenIsNotNull(){
        lenient().when(clienteRepository.findByTipoDocumentoAndDocumento("CC","123456")).thenReturn(Optional.ofNullable(clienteEntity));
        lenient().when(clienteMapperMysql.mapToModel(clienteEntity)).thenReturn(cliente);

        Assertions.assertEquals(cliente, clienteAdapter.getByTipoDocumentoAndDocumento("CC","123456"));

        verify(clienteMapperMysql).mapToModel(clienteEntity);
        verify(clienteRepository).findByTipoDocumentoAndDocumento("CC","123456");
    }
}
