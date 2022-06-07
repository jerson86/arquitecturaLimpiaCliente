package com.pragma.infrastructure;

import com.pragma.domain.model.Cliente;
import com.pragma.domain.model.Imagen;
import com.pragma.infrastructure.mongo.entity.ImageEntity;
import com.pragma.infrastructure.mongo.mapper.ImagenMapperMongo;
import com.pragma.infrastructure.mysql.entity.ClienteEntity;
import com.pragma.infrastructure.mysql.mapper.ClienteMapperMysql;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MapperTests {

    private ImageEntity imageEntity;

    private Imagen imagen;

    private Cliente cliente;
    private ClienteEntity clienteEntity;

    @Mock
    private ImagenMapperMongo imagenMapper;

    private ModelMapper modelMapper = new ModelMapper();
    @Mock
    private ClienteMapperMysql clienteMapperMysql;

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
        imagen.setId("0");
        imagen.setFoto("hola.jpg");
        imagen.setCliente(cliente);

        imageEntity = new ImageEntity();
        imageEntity.setId("0");
        imageEntity.setFoto("hola.jpg");
        imageEntity.setCliente(clienteEntity);
    }

    @Test
    void mapperToModelCliente(){
        lenient().when(clienteMapperMysql.mapToModel(clienteEntity)).thenReturn(cliente);
        //lenient().when(modelMapper.map(clienteEntity,Cliente.class)).thenReturn(cliente);

        //Assertions.assertEquals(cliente, clienteMapperMysql.mapToModel(clienteEntity));
        Assertions.assertEquals(cliente, modelMapper.map(clienteEntity,Cliente.class));

        //verify(clienteMapperMysql).mapToModel(clienteEntity);
        //verify(modelMapper).map(clienteEntity,Cliente.class);
    }
}
