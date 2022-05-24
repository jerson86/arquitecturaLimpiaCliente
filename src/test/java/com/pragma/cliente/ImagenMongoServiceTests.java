package com.pragma.cliente;

import com.pragma.cliente.dto.ImagenMongoDTO;
import com.pragma.cliente.entity.Cliente;
import com.pragma.cliente.entity.ETipoDocumento;
import com.pragma.cliente.entity.ImagenMongo;
import com.pragma.cliente.mappers.ImagenMongoMapper;
import com.pragma.cliente.repository.ClienteRepository;
import com.pragma.cliente.repository.ImagenMongoRepository;
import com.pragma.cliente.service.ImagenMongoServiceImpl;
import com.pragma.cliente.service.ImagenServiceImpl;
import com.pragma.cliente.service.NextSequenceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ImagenMongoServiceTests {
    @InjectMocks
    private ImagenMongoServiceImpl imagenService;

    private ImagenMongoDTO imagenDTO;

    private ImagenMongo imagen;

    private Cliente cliente;

    @Mock
    private ImagenMongoMapper imagenMapper;

    @Mock
    private ImagenMongoRepository imagenRepository;
    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private NextSequenceService nextSequenceService;

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

        imagen = new ImagenMongo();
        imagen.setId(nextSequenceService.getNextSequence("imagenes"));
        imagen.setFoto("hola.jpg");
        imagen.setCliente(cliente);

        imagenDTO = new ImagenMongoDTO();
        imagenDTO.setId(nextSequenceService.getNextSequence("imagenes"));
        imagenDTO.setFoto("hola.jpg");
        imagenDTO.setCliente(cliente);
    }

    @DisplayName("JUnit test para el metodo createCliente")
    @Test
    void createImagenTest() throws IOException {

        when(imagenMapper.mapToEntity(imagenDTO)).thenReturn(imagen);
        when(imagenRepository.save(imagen)).thenReturn(imagen);
        when(imagenMapper.mapToDTO(imagen)).thenReturn(imagenDTO);
        when(clienteRepository.findById(1L)).thenReturn(Optional.ofNullable(cliente));

        MockMultipartFile foto = new MockMultipartFile("data", "hola.jpg", "text/plain", "some xml".getBytes());
        Assertions.assertEquals(imagenDTO, imagenService.createImagen(foto,1L));

        verify(imagenMapper).mapToEntity(imagenDTO);
        verify(imagenMapper).mapToDTO(imagen);
        verify(clienteRepository).findById(1L);
        verify(imagenRepository).save(imagen);
    }

    @Test
    void getAllImagenesTest()
    {
        when(imagenRepository.findAll()).thenReturn(List.of(imagen));
        when(imagenMapper.mapToDTO(imagen)).thenReturn(imagenDTO);

        Assertions.assertEquals(List.of(imagenDTO), imagenService.getAllImagenes());

        verify(imagenMapper).mapToDTO(imagen);
        verify(imagenRepository).findAll();
    }

    @Test
    void getImagenByIdTest()
    {
        when(imagenRepository.findById("0")).thenReturn(Optional.ofNullable(imagen));
        when(imagenMapper.mapToDTO(imagen)).thenReturn(imagenDTO);

        Assertions.assertEquals(imagenDTO, imagenService.getImagenById("0"));

        verify(imagenMapper).mapToDTO(imagen);
        verify(imagenRepository).findById("0");
    }


    @Test
    void updateImagenTest()
    {
        when(imagenRepository.save(imagen)).thenReturn(imagen);
        when(imagenRepository.findById("0")).thenReturn(Optional.ofNullable(imagen));
        when(imagenMapper.mapToDTO(imagen)).thenReturn(imagenDTO);

        MockMultipartFile foto = new MockMultipartFile("data", "hola.jpg", "text/plain", "some xml".getBytes());
        Assertions.assertEquals(imagenDTO, imagenService.updateImagen("0",foto,1L));

        verify(imagenMapper).mapToDTO(imagen);
        verify(imagenRepository).findById("0");
        verify(imagenRepository).save(imagen);
    }

    @Test
    void deleteImagenTest()
    {
        when(imagenRepository.findById("0")).thenReturn(Optional.ofNullable(imagen));
        when(imagenMapper.mapToDTO(imagen)).thenReturn(imagenDTO);
        //when(clienteRepository.delete(cliente));

        Assertions.assertEquals(imagenDTO, imagenService.deleteImagen("0"));

        verify(imagenMapper).mapToDTO(imagen);
        verify(imagenRepository).findById("0");
        verify(imagenRepository).delete(imagen);
    }
}
