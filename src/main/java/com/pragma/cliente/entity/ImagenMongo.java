package com.pragma.cliente.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.Instant;
@Data
@Document(collection = "imagenes")
public class ImagenMongo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String foto;
    @CreationTimestamp
    private Instant fechaRegistro;
    private Cliente cliente;
    private int seq;
}
