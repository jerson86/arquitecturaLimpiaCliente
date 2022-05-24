package com.pragma.cliente.service;


import com.pragma.cliente.dto.ClienteDTO;
import com.pragma.cliente.dto.ImagenDTO;
import com.pragma.cliente.entity.Cliente;
import com.pragma.cliente.entity.Imagen;
import com.pragma.cliente.exception.ResourceNotFoundException;
import com.pragma.cliente.mappers.ImagenMapper;
import com.pragma.cliente.repository.ClienteRepository;
import com.pragma.cliente.repository.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImagenServiceImpl implements ImagenService{
    @Autowired
    private ImagenRepository imagenRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ImagenMapper imagenMapper;

    @Override
    public List<ImagenDTO> getAllImagenes() {
        List<Imagen> imagenes = imagenRepository.findAll();
        imagenes.stream().findFirst().orElseThrow(()->
                new ResourceNotFoundException("imagen",1L)
        );
        // convert entity to DTO
        List<ImagenDTO> imagenDTOS= new ArrayList<>();
        imagenes.forEach(imagen -> {
            ImagenDTO imagenResponse = imagenMapper.mapToDTO(imagen);
            imagenDTOS.add(imagenResponse);
        });

        return imagenDTOS;
    }

    @Override
    public ImagenDTO createImagen(MultipartFile foto, Long idCliente) throws IOException {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(()-> new ResourceNotFoundException("cliente",idCliente));
        //convierto la imagen a base64
        /*byte[] imageByte = Base64.encodeBase64(foto.getBytes());
        String imageString =  new String(imageByte);
        System.out.println("tamaño imagen:"+imageString.length());*/

        ImagenDTO imagenDTO = new ImagenDTO();
        imagenDTO.setCliente(cliente);
        imagenDTO.setFoto(foto.getOriginalFilename());
        // convert DTO to Entity
        Imagen imagenRequest = imagenMapper.mapToEntity(imagenDTO);
        imagenRepository.save(imagenRequest);
        // convert entity to DTO
        ImagenDTO imagenResponse = imagenMapper.mapToDTO(imagenRequest);
        return imagenResponse;
    }

    @Override
    public ImagenDTO updateImagen(long id, MultipartFile foto, Long idCliente) {
        Imagen imagen = imagenRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("imagen",id));
        imagen.setFoto(foto.getOriginalFilename());
        imagen.setId(id);

        imagenRepository.save(imagen);
        // convert entity to DTO
        ImagenDTO imagenResponse = imagenMapper.mapToDTO(imagen);

        return imagenResponse;
    }

    @Override
    public ImagenDTO deleteImagen(long id) {
        Imagen imagen = imagenRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("imagen",id));
        imagenRepository.delete(imagen);
        // convert entity to DTO
        ImagenDTO imagenResponse = imagenMapper.mapToDTO(imagen);
        return imagenResponse;
    }

    @Override
    public ImagenDTO getImagenById(long id) {
        Imagen imagen = imagenRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("imagen",id));

        // convert entity to DTO
        ImagenDTO imagenResponse = imagenMapper.mapToDTO(imagen);
        return imagenResponse;
    }

    @Override
    public ImagenDTO getImagenByCliente(ClienteDTO clienteDTO) {
        Imagen imagen = imagenRepository.findByCliente(clienteDTO).orElseThrow(()-> new ResourceNotFoundException("imagen",clienteDTO.getId()));

        // convert entity to DTO
        ImagenDTO imagenResponse = imagenMapper.mapToDTO(imagen);
        return imagenResponse;
    }

}
