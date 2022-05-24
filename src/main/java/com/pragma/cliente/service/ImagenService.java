package com.pragma.cliente.service;

import com.pragma.cliente.dto.ClienteDTO;
import com.pragma.cliente.dto.ImagenDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImagenService {
    List<ImagenDTO> getAllImagenes();

    ImagenDTO createImagen(MultipartFile foto, Long idCliente) throws IOException;

    ImagenDTO updateImagen(long id, MultipartFile foto, Long idCliente);

    ImagenDTO deleteImagen(long id);

    ImagenDTO getImagenById(long id);

    ImagenDTO getImagenByCliente(ClienteDTO clienteDTO);
}
