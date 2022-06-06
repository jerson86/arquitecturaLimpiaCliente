package com.pragma.infrastructure.mongo.repository;

import com.pragma.domain.model.Cliente;
import com.pragma.infrastructure.mongo.entity.ImageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ImagenRepository extends MongoRepository<ImageEntity,String> {
    Optional<ImageEntity> findByCliente(Cliente cliente);
}
