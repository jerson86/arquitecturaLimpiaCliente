package com.pragma.application.mapper;

import com.pragma.application.dto.ClienteDTO;
import com.pragma.application.dto.ImagenDTO;
import com.pragma.domain.model.Cliente;
import com.pragma.domain.model.Imagen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImagenMapper {
    @Autowired
    private ModelMapper modelMapper;

    // convert DTO to Entity
    public Imagen mapToModel(ImagenDTO imagenDTO){
        return modelMapper.map(imagenDTO,Imagen.class);
    }
    // convert entity to DTO
    public ImagenDTO mapToDTO(Imagen imagen){
        return modelMapper.map(imagen, ImagenDTO.class);
    }

    public List<Imagen> mapToModelList(List<ImagenDTO> imagenesDTO){
        List<Imagen> imagenes= new ArrayList<>();
        imagenesDTO.forEach(imagen -> {
            Imagen imagenResponse = this.mapToModel(imagen);
            imagenes.add(imagenResponse);
        });
        return imagenes;
    }

    public List<ImagenDTO> mapToDTOList(List<Imagen> imagenes){

        List<ImagenDTO> imagenesDTO = new ArrayList<>();
        imagenes.forEach(imagen -> {
            ImagenDTO imagenResponse = this.mapToDTO(imagen);
            imagenesDTO.add(imagenResponse);
        });
        return imagenesDTO;
    }
}
