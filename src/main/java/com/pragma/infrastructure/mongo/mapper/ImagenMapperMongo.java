package com.pragma.infrastructure.mongo.mapper;

import com.pragma.domain.model.Cliente;
import com.pragma.domain.model.Imagen;
import com.pragma.infrastructure.mongo.entity.ImageEntity;
import com.pragma.infrastructure.mysql.entity.ClienteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImagenMapperMongo {
    @Autowired
    private ModelMapper modelMapper;

    // convert DTO to Entity
    public ImageEntity mapToEntity(Imagen imagen){
        return modelMapper.map(imagen,ImageEntity.class);
    }
    // convert entity to Model
    public Imagen mapToModel(ImageEntity imageEntity){
        return modelMapper.map(imageEntity, Imagen.class);
    }
}
