package com.pragma.domain.port.outbound;

import com.pragma.domain.model.Imagen;

import java.util.Base64;
import java.util.List;

public interface PersistImagen {
    Imagen save(String foto, long idCliente);
    Imagen update(String id, String foto, long idCliente);
    Imagen delete(String id);
    Imagen getById(String id);
    List<Imagen> getAll();
}
