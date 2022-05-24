package com.pragma.cliente.mappers;

import com.pragma.cliente.dto.ImagenDTO;
import com.pragma.cliente.entity.Imagen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImagenMapper {
    @Autowired
    private ModelMapper modelMapper;

    // convert DTO to Entity
    public Imagen mapToEntity(ImagenDTO imagenDTO){
        return modelMapper.map(imagenDTO,Imagen.class);
    }
    // convert entity to DTO
    public ImagenDTO mapToDTO(Imagen imagen){
        return modelMapper.map(imagen, ImagenDTO.class);
    }
}
