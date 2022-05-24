package com.pragma.cliente;

import com.pragma.cliente.dto.ClienteDTO;
import com.pragma.cliente.entity.Cliente;
import com.pragma.cliente.entity.ETipoDocumento;
import com.pragma.cliente.mappers.ClienteMapper;
import com.pragma.cliente.repository.ClienteRepository;
import com.pragma.cliente.service.ClienteServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTests {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private ClienteDTO clienteDTO;

    private Cliente cliente;

    @Mock
    private ClienteMapper clienteMapper;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp()
    {
        cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombres("Cliente");
        cliente.setApellidos("De Prueba");
        cliente.setDocumento("1234567890");
        cliente.setTipoDocumento(ETipoDocumento.CC);
        cliente.setCiudadNacimiento("Ocaña");
        cliente.setEdad((short) 22);

        clienteDTO = new ClienteDTO();
        clienteDTO.setId(1);
        clienteDTO.setNombres("Cliente");
        clienteDTO.setApellidos("De Prueba");
        clienteDTO.setDocumento("1234567890");
        clienteDTO.setTipoDocumento(ETipoDocumento.CC);
        clienteDTO.setCiudadNacimiento("Ocaña");
        clienteDTO.setEdad((short) 22);
    }

    @DisplayName("JUnit test para el metodo createCliente")
    @Test
    void createClienteTest()
    {

        when(clienteMapper.mapToEntity(clienteDTO)).thenReturn(cliente);
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(clienteMapper.mapToDTO(cliente)).thenReturn(clienteDTO);

        Assertions.assertEquals(clienteDTO, clienteService.createCliente(clienteDTO));

        verify(clienteMapper).mapToEntity(clienteDTO);
        verify(clienteMapper).mapToDTO(cliente);
        verify(clienteRepository).save(cliente);
    }

   @Test
    void getAllClientesTest()
    {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));
        when(clienteMapper.mapToDTO(cliente)).thenReturn(clienteDTO);

        Assertions.assertEquals(List.of(clienteDTO), clienteService.getAllClientes());

        verify(clienteMapper).mapToDTO(cliente);
        verify(clienteRepository).findAll();
    }

    @Test
    void getClienteByIdTest()
    {
        when(clienteRepository.findById(1L)).thenReturn(Optional.ofNullable(cliente));
        when(clienteMapper.mapToDTO(cliente)).thenReturn(clienteDTO);

        Assertions.assertEquals(clienteDTO, clienteService.getClienteById(1L));

        verify(clienteMapper).mapToDTO(cliente);
        verify(clienteRepository).findById(1L);
    }


    @Test
    void findByTipoDocumentoAndDocumentoTest()
    {
        when(clienteRepository.findByTipoDocumentoAndDocumento("CC","1234567890")).thenReturn(Optional.ofNullable(cliente));
        when(clienteMapper.mapToDTO(cliente)).thenReturn(clienteDTO);

        Assertions.assertEquals(clienteDTO, clienteService.findByTipoDocumentoAndDocumento("CC","1234567890"));

        verify(clienteMapper).mapToDTO(cliente);
        verify(clienteRepository).findByTipoDocumentoAndDocumento("CC","1234567890");
    }

    @Test
    void updateClienteTest()
    {

        when(clienteMapper.mapToEntity(clienteDTO)).thenReturn(cliente);
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(clienteRepository.findById(1L)).thenReturn(Optional.ofNullable(cliente));
        when(clienteMapper.mapToDTO(cliente)).thenReturn(clienteDTO);


        Assertions.assertEquals(clienteDTO, clienteService.updateCliente(1L,clienteDTO));

        verify(clienteMapper).mapToEntity(clienteDTO);
        verify(clienteMapper).mapToDTO(cliente);
        verify(clienteRepository).findById(1L);
        verify(clienteRepository).save(cliente);
    }

    @Test
    void deleteClienteTest()
    {
        when(clienteRepository.findById(1L)).thenReturn(Optional.ofNullable(cliente));
        when(clienteMapper.mapToDTO(cliente)).thenReturn(clienteDTO);
        //when(clienteRepository.delete(cliente));

        Assertions.assertEquals(clienteDTO, clienteService.deleteCliente(1L));

        verify(clienteMapper).mapToDTO(cliente);
        verify(clienteRepository).findById(1L);
        verify(clienteRepository).delete(cliente);
    }
}




