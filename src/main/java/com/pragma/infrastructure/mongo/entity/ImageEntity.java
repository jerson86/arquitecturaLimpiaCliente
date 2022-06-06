package com.pragma.infrastructure.mongo.entity;

import com.pragma.infrastructure.mysql.entity.ClienteEntity;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.Instant;

@Data
@Document(collection = "imagenes")
public class ImageEntity {
    @Id
    private String id;
    private String foto;
    @CreationTimestamp
    private Instant fechaRegistro;
    private ClienteEntity cliente;
    private int seq;
}
