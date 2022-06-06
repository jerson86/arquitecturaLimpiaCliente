package com.pragma.domain;

import com.pragma.domain.model.Cliente;
import com.pragma.domain.model.Imagen;
import com.pragma.domain.port.outbound.PersistImagen;
import com.pragma.domain.service.ClienteServiceImpl;
import com.pragma.domain.service.ImagenServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class ImagenServiceTests {

    private Imagen imagen;
    private Cliente cliente;

    @Mock
    private PersistImagen persistImagen;

    @InjectMocks
    private ImagenServiceImpl imagenServiceImpl;

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
    void givenSaveImagenWhenIsNotNull(){
        lenient().when(imagenServiceImpl.createImage("foto.jpg",1L)).thenReturn(imagen);
    }

    @Test
    void givenUpdateImagenWhenIsNotNull(){
        lenient().when(imagenServiceImpl.updateImagen("1","foto.jpg",1L)).thenReturn(imagen);
    }

    @Test
    void givenDeleteImagenWhenIsNotNull(){
        lenient().when(imagenServiceImpl.deleteImagen("1")).thenReturn(imagen);
    }

    @Test
    void givenGetByIdImagenWhenIsNotNull(){
        lenient().when(imagenServiceImpl.getImagenById("1")).thenReturn(imagen);
    }

    @Test
    void givenGetAllmagenWhenIsNotNull(){
        lenient().when(imagenServiceImpl.getAllImages()).thenReturn(List.of(imagen));
    }
}
