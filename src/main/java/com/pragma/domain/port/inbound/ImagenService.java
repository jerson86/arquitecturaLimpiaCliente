package com.pragma.domain.port.inbound;

import com.pragma.domain.model.Imagen;

import java.util.Base64;
import java.util.List;

public interface ImagenService {
    List<Imagen> getAllImages();

    Imagen createImage(String foto, long idCliente);

    Imagen updateImagen(String id, String foto, long idCliente);

    Imagen deleteImagen(String id);

    Imagen getImagenById(String id);
}
