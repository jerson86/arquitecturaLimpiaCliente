package com.pragma.application.dto;

import com.pragma.domain.model.Cliente;
import lombok.Data;

import java.util.Base64;

@Data
public class ImagenDTO {
    private String id;
    private String foto;
    private Cliente cliente;
}
