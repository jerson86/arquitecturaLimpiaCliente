package com.pragma.domain.service;

import com.pragma.domain.model.Imagen;
import com.pragma.domain.port.inbound.ImagenService;
import com.pragma.domain.port.outbound.PersistImagen;

import java.util.Base64;
import java.util.List;

public class ImagenServiceImpl implements ImagenService {

    private final PersistImagen persistImagen;

    public ImagenServiceImpl(PersistImagen persistImagen) {
        this.persistImagen = persistImagen;
    }

    @Override
    public List<Imagen> getAllImages() {
        return persistImagen.getAll();
    }

    @Override
    public Imagen createImage(String foto, long idCiente) {

        return persistImagen.save(foto, idCiente);
    }

    @Override
    public Imagen updateImagen(String id, String foto, long idCliente) {
        return persistImagen.update(id,foto,idCliente);
    }

    @Override
    public Imagen deleteImagen(String id) {
        return persistImagen.delete(id);
    }

    @Override
    public Imagen getImagenById(String id) {
        return persistImagen.getById(id);
    }
}
