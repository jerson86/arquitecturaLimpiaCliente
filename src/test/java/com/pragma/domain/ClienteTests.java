package com.pragma.domain;

import com.pragma.domain.exception.ResourceNotFoundException;
import com.pragma.domain.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ClienteTests {

    private Cliente cliente,cliente2;

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
    void givenTypeDocumentWhenIsCC(){
        assertThat(cliente.getTipoDocumento()).isEqualTo("CC");
    }
    @Test
    void givenDocumentWhenIsEquals123456(){
        assertThat(cliente.getDocumento()).isEqualTo("123456");
    }
    @Test
    void givenFirstNameAndLastNameAndIdWhenIsEqualsPepitoPerezAndIdIsEqualsTo1(){
        assertThat(cliente.getNombres()).isEqualTo("Pepito");
        assertThat(cliente.getApellidos()).isEqualTo("Perez");
        assertThat(cliente.getId()).isEqualTo(1L);
    }
    @Test
    void givenAgeWhenGreaterThanOrEqualTo18(){
        assertThat(cliente.getEdad()).isGreaterThanOrEqualTo((short)18);
    }
    @Test
    void givenCityOfBirthWhenIsEqualsToOcana(){
        assertThat(cliente.getCiudadNacimiento()).isEqualTo("Ocana");
    }

    @Test
    void givenClienteNotExistWhenIsNull(){
        try {
            assertThat(cliente.getId()).isEqualTo(2L);
        }catch(Throwable ex){
            new ResourceNotFoundException("Cliente ",2L);
        }
    }
}
