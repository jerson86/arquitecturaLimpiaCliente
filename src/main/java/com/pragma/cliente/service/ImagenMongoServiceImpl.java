package com.pragma.cliente.service;

import com.pragma.cliente.dto.ClienteDTO;
import com.pragma.cliente.dto.ImagenDTO;
import com.pragma.cliente.dto.ImagenMongoDTO;
import com.pragma.cliente.entity.Cliente;
import com.pragma.cliente.entity.Imagen;
import com.pragma.cliente.entity.ImagenMongo;
import com.pragma.cliente.exception.ResourceNotFoundException;
import com.pragma.cliente.mappers.ImagenMapper;
import com.pragma.cliente.mappers.ImagenMongoMapper;
import com.pragma.cliente.repository.ClienteRepository;
import com.pragma.cliente.repository.ImagenMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImagenMongoServiceImpl implements ImagenMongoService{
    @Autowired
    private ImagenMongoRepository imagenRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ImagenMongoMapper imagenMapper;
    @Autowired
    private NextSequenceService nextSequenceService;

    @Override
    public List<ImagenMongoDTO> getAllImagenes() {
        List<ImagenMongo> imagenes = imagenRepository.findAll();
        imagenes.stream().findFirst().orElseThrow(()->
                new ResourceNotFoundException("imagen Mongo",1L)
        );
        // convert entity to DTO
        List<ImagenMongoDTO> imagenDTOS= new ArrayList<>();
        imagenes.forEach(imagenMongo -> {
            ImagenMongoDTO imagenResponse = imagenMapper.mapToDTO(imagenMongo);
            imagenDTOS.add(imagenResponse);
        });

        return imagenDTOS;
    }

    @Override
    public ImagenMongoDTO createImagen(MultipartFile foto, Long idCliente){
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(()-> new ResourceNotFoundException("cliente",idCliente));

        ImagenMongoDTO imagenDTO = new ImagenMongoDTO();
        imagenDTO.setId(nextSequenceService.getNextSequence("imagenes"));
        imagenDTO.setCliente(cliente);
        imagenDTO.setFoto(foto.getOriginalFilename());
        // convert DTO to Entity
        ImagenMongo imagenRequest = imagenMapper.mapToEntity(imagenDTO);
        imagenRepository.save(imagenRequest);
        // convert entity to DTO
        ImagenMongoDTO imagenResponse = imagenMapper.mapToDTO(imagenRequest);
        return imagenResponse;
    }

    @Override
    public ImagenMongoDTO updateImagen(String id, MultipartFile foto, Long idCliente) {
        ImagenMongo imagen = imagenRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("imagen",Long.parseLong(id)));
        imagen.setFoto(foto.getOriginalFilename());
        imagen.setId(id);

        imagenRepository.save(imagen);
        // convert entity to DTO
        ImagenMongoDTO imagenResponse = imagenMapper.mapToDTO(imagen);

        return imagenResponse;
    }

    @Override
    public ImagenMongoDTO deleteImagen(String id) {
        ImagenMongo imagen = imagenRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("imagen",Long.parseLong(id)));
        imagenRepository.delete(imagen);
        // convert entity to DTO
        ImagenMongoDTO imagenResponse = imagenMapper.mapToDTO(imagen);
        return imagenResponse;
    }

    @Override
    public ImagenMongoDTO getImagenById(String id) {
        ImagenMongo imagen = imagenRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("imagen",Long.parseLong(id)));

        // convert entity to DTO
        ImagenMongoDTO imagenResponse = imagenMapper.mapToDTO(imagen);
        return imagenResponse;
    }

    @Override
    public ImagenMongoDTO getImagenByCliente(ClienteDTO clienteDTO) {
        ImagenMongo imagen = imagenRepository.findByCliente(clienteDTO).orElseThrow(()-> new ResourceNotFoundException("imagen",clienteDTO.getId()));

        // convert entity to DTO
        ImagenMongoDTO imagenResponse = imagenMapper.mapToDTO(imagen);
        return imagenResponse;
    }
}
