package com.pragma.cliente;

import com.pragma.cliente.dto.ImagenDTO;
import com.pragma.cliente.entity.Cliente;
import com.pragma.cliente.entity.ETipoDocumento;
import com.pragma.cliente.entity.Imagen;
import com.pragma.cliente.mappers.ImagenMapper;
import com.pragma.cliente.repository.ClienteRepository;
import com.pragma.cliente.repository.ImagenRepository;
import com.pragma.cliente.service.ImagenServiceImpl;
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
public class ImagenServiceTests {
    @InjectMocks
    private ImagenServiceImpl imagenService;

    private ImagenDTO imagenDTO;

    private Imagen imagen;

    private Cliente cliente;

    @Mock
    private ImagenMapper imagenMapper;

    @Mock
    private ImagenRepository imagenRepository;
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

        imagen = new Imagen();
        imagen.setId(0);
        imagen.setFoto("hola.jpg");
        imagen.setCliente(cliente);

        imagenDTO = new ImagenDTO();
        imagenDTO.setId(0);
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
        when(imagenRepository.findById(1L)).thenReturn(Optional.ofNullable(imagen));
        when(imagenMapper.mapToDTO(imagen)).thenReturn(imagenDTO);

        Assertions.assertEquals(imagenDTO, imagenService.getImagenById(1L));

        verify(imagenMapper).mapToDTO(imagen);
        verify(imagenRepository).findById(1L);
    }


    @Test
    void updateImagenTest()
    {

        when(imagenRepository.save(imagen)).thenReturn(imagen);
        when(imagenRepository.findById(0L)).thenReturn(Optional.ofNullable(imagen));
        when(imagenMapper.mapToDTO(imagen)).thenReturn(imagenDTO);

        MockMultipartFile foto = new MockMultipartFile("data", "hola.jpg", "text/plain", "some xml".getBytes());
        Assertions.assertEquals(imagenDTO, imagenService.updateImagen(0L,foto,1L));

        verify(imagenMapper).mapToDTO(imagen);
        verify(imagenRepository).findById(0L);
        verify(imagenRepository).save(imagen);
    }

    @Test
    void deleteImagenTest()
    {
        when(imagenRepository.findById(1L)).thenReturn(Optional.ofNullable(imagen));
        when(imagenMapper.mapToDTO(imagen)).thenReturn(imagenDTO);
        //when(clienteRepository.delete(cliente));

        Assertions.assertEquals(imagenDTO, imagenService.deleteImagen(1L));

        verify(imagenMapper).mapToDTO(imagen);
        verify(imagenRepository).findById(1L);
        verify(imagenRepository).delete(imagen);
    }
}
