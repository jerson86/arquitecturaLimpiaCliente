package com.pragma.infrastructure.mongo.adapter;

import com.pragma.domain.exception.ResourceNotFoundException;
import com.pragma.domain.model.Cliente;
import com.pragma.domain.model.Imagen;
import com.pragma.domain.port.outbound.PersistImagen;
import com.pragma.infrastructure.mongo.entity.ImageEntity;
import com.pragma.infrastructure.mongo.mapper.ImagenMapperMongo;
import com.pragma.infrastructure.mongo.repository.ImagenRepository;
import com.pragma.infrastructure.mysql.entity.ClienteEntity;
import com.pragma.infrastructure.mysql.mapper.ClienteMapperMysql;
import com.pragma.infrastructure.mysql.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class ImagenAdapter implements PersistImagen {

    @Autowired
    private ImagenRepository imagenRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ImagenMapperMongo imagenMapperMongo;
    @Autowired
    private NextSequenceAdapter nextSequenceAdapter;

    public ImagenAdapter(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    @Override
    public Imagen save(String foto, long idCliente) {
        ClienteEntity cliente = clienteRepository.findById(idCliente).orElseThrow(()-> new ResourceNotFoundException("cliente",idCliente));

        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setId(nextSequenceAdapter.getNextSequence("imagenes"));
        imageEntity.setCliente(cliente);
        imageEntity.setFoto(foto);
        // convert DTO to Entity
        //Imagen imagenRequest = imagenMapperMongo.mapToEntity(imageEntity);
        imagenRepository.save(imageEntity);
        // convert entity to Model
        Imagen imagenResponse = imagenMapperMongo.mapToModel(imageEntity);
        return imagenResponse;
    }

    @Override
    public Imagen update(String id, String foto, long idCliente) {
        ImageEntity imageEntity = imagenRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("imagen",Long.parseLong(id)));
        imageEntity.setFoto(foto);
        imageEntity.setId(id);

        imagenRepository.save(imageEntity);
        // convert entity to DTO
        Imagen imagenResponse = imagenMapperMongo.mapToModel(imageEntity);

        return imagenResponse;
    }

    @Override
    public Imagen delete(String id) {
        ImageEntity imageEntity = imagenRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("imagen",Long.parseLong(id)));
        imagenRepository.delete(imageEntity);
        // convert entity to DTO
        Imagen imagenResponse = imagenMapperMongo.mapToModel(imageEntity);
        return imagenResponse;
    }

    @Override
    public Imagen getById(String id) {
        ImageEntity imageEntity = imagenRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("imagen",Long.parseLong(id)));

        // convert entity to DTO
        Imagen imagenResponse = imagenMapperMongo.mapToModel(imageEntity);
        return imagenResponse;
    }

    @Override
    public List<Imagen> getAll() {
        List<ImageEntity> imagenesEnt = imagenRepository.findAll();
        imagenesEnt.stream().findFirst().orElseThrow(()->
                new ResourceNotFoundException("imagen",1L)
        );
        // convert entity to DTO
        List<Imagen> imagenes= new ArrayList<>();
        imagenesEnt.forEach(imagen -> {
            Imagen imagenResponse = imagenMapperMongo.mapToModel(imagen);
            imagenes.add(imagenResponse);
        });

        return imagenes;
    }
}
