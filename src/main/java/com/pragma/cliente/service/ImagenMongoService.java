package com.pragma.cliente.service;

import com.pragma.cliente.dto.ClienteDTO;
import com.pragma.cliente.dto.ImagenMongoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImagenMongoService {
    List<ImagenMongoDTO> getAllImagenes();

    ImagenMongoDTO createImagen(MultipartFile foto, Long idCliente) throws IOException;

    ImagenMongoDTO updateImagen(String id, MultipartFile foto, Long idCliente);

    ImagenMongoDTO deleteImagen(String id);

    ImagenMongoDTO getImagenById(String id);

    ImagenMongoDTO getImagenByCliente(ClienteDTO clienteDTO);
}
