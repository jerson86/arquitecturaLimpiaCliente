package com.pragma.cliente.repository;

import com.pragma.cliente.dto.ClienteDTO;
import com.pragma.cliente.entity.Imagen;
import com.pragma.cliente.entity.ImagenMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ImagenMongoRepository extends MongoRepository<ImagenMongo, String> {
    Optional<ImagenMongo> findByCliente(ClienteDTO clienteDTO);
}
