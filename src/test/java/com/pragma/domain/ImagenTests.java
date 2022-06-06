package com.pragma.domain;

import com.pragma.domain.model.Cliente;
import com.pragma.domain.model.Imagen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ImagenTests {

    private Imagen imagen;
    private Cliente cliente;

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

        imagen = new Imagen();
        imagen.setId("1");
        imagen.setFoto("foto.jpg");
        imagen.setCliente(cliente);
    }

    @Test
    void givenImageWhenIsEqualsToFotoJpg(){
        assertThat(imagen.getFoto()).isEqualTo("foto.jpg");
    }
    @Test
    void givenIdImagenAndIdClienteWhenIsEqualsToIdFoto1AndIdCliente1(){
        assertThat(imagen.getId()).isEqualTo("1");
        assertThat(imagen.getCliente().getId()).isEqualTo(1L);
    }

}
