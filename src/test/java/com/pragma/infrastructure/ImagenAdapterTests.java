package com.pragma.infrastructure;

import com.pragma.domain.model.Cliente;
import com.pragma.domain.model.Imagen;
import com.pragma.infrastructure.mongo.adapter.ImagenAdapter;
import com.pragma.infrastructure.mongo.adapter.NextSequenceAdapter;
import com.pragma.infrastructure.mongo.entity.ImageEntity;
import com.pragma.infrastructure.mongo.mapper.ImagenMapperMongo;
import com.pragma.infrastructure.mongo.repository.ImagenRepository;
import com.pragma.infrastructure.mysql.adapter.ClienteAdapter;
import com.pragma.infrastructure.mysql.entity.ClienteEntity;
import com.pragma.infrastructure.mysql.repository.ClienteRepository;
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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ImagenAdapterTests {

    private ImageEntity imageEntity;

    private Imagen imagen;

    private Cliente cliente;
    private ClienteEntity clienteEntity;

    @Mock
    private ImagenMapperMongo imagenMapper;

    @Mock
    private ImagenRepository imagenRepository;
    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private NextSequenceAdapter nextSequenceService;

    @InjectMocks
    private ImagenAdapter imagenAdapter = new ImagenAdapter(imagenRepository);

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

        imagen = new Imagen();
        imagen.setId(nextSequenceService.getNextSequence("imagenes"));
        imagen.setFoto("hola.jpg");
        imagen.setCliente(cliente);

        imageEntity = new ImageEntity();
        imageEntity.setId(nextSequenceService.getNextSequence("imagenes"));
        imageEntity.setFoto("hola.jpg");
        imageEntity.setCliente(clienteEntity);
    }

    @DisplayName("JUnit test para el metodo createCliente")
    @Test
    void createImagenTest() {

        lenient().when(imagenRepository.save(imageEntity)).thenReturn(imageEntity);
        lenient().when(imagenMapper.mapToModel(imageEntity)).thenReturn(imagen);
        lenient().when(clienteRepository.findById(1L)).thenReturn(Optional.ofNullable(clienteEntity));

        Assertions.assertEquals(imagen, imagenAdapter.save(imagen.getFoto(),1L));

        verify(imagenMapper).mapToModel(imageEntity);
        verify(clienteRepository).findById(1L);
        verify(imagenRepository).save(imageEntity);
    }

    @Test
    void getAllImagenesTest()
    {
        when(imagenRepository.findAll()).thenReturn(List.of(imageEntity));
        when(imagenMapper.mapToModel(imageEntity)).thenReturn(imagen);

        Assertions.assertEquals(List.of(imagen), imagenAdapter.getAll());

        verify(imagenMapper).mapToModel(imageEntity);
        verify(imagenRepository).findAll();
    }

    @Test
    void getImagenByIdTest()
    {
        when(imagenRepository.findById("0")).thenReturn(Optional.ofNullable(imageEntity));
        when(imagenMapper.mapToModel(imageEntity)).thenReturn(imagen);

        Assertions.assertEquals(imagen, imagenAdapter.getById("0"));

        verify(imagenMapper).mapToModel(imageEntity);
        verify(imagenRepository).findById("0");
    }


    @Test
    void updateImagenTest()
    {
        when(imagenRepository.save(imageEntity)).thenReturn(imageEntity);
        when(imagenRepository.findById("0")).thenReturn(Optional.ofNullable(imageEntity));
        when(imagenMapper.mapToModel(imageEntity)).thenReturn(imagen);

        Assertions.assertEquals(imagen, imagenAdapter.update("0",imagen.getFoto(),1L));

        verify(imagenMapper).mapToModel(imageEntity);
        verify(imagenRepository).findById("0");
        verify(imagenRepository).save(imageEntity);
    }

    @Test
    void deleteImagenTest()
    {
        when(imagenRepository.findById("0")).thenReturn(Optional.ofNullable(imageEntity));
        when(imagenMapper.mapToModel(imageEntity)).thenReturn(imagen);
        //when(clienteRepository.delete(cliente));

        Assertions.assertEquals(imagen, imagenAdapter.delete("0"));

        verify(imagenMapper).mapToModel(imageEntity);
        verify(imagenRepository).findById("0");
        verify(imagenRepository).delete(imageEntity);
    }
}
