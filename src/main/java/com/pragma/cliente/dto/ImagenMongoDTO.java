package com.pragma.cliente.dto;

import com.pragma.cliente.entity.Cliente;
import lombok.Data;

@Data
public class ImagenMongoDTO {
    private String id;
    private String foto;
    private Cliente cliente;
}
