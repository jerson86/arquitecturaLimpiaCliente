package com.pragma.cliente.mappers;

import com.pragma.cliente.dto.ImagenDTO;
import com.pragma.cliente.dto.ImagenMongoDTO;
import com.pragma.cliente.entity.Imagen;
import com.pragma.cliente.entity.ImagenMongo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImagenMongoMapper {
    @Autowired
    private ModelMapper modelMapper;

    // convert DTO to Entity
    public ImagenMongo mapToEntity(ImagenMongoDTO imagenMongoDTO){
        return modelMapper.map(imagenMongoDTO,ImagenMongo.class);
    }
    // convert entity to DTO
    public ImagenMongoDTO mapToDTO(ImagenMongo imagenMongo){
        return modelMapper.map(imagenMongo, ImagenMongoDTO.class);
    }
}
