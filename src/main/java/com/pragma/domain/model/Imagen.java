package com.pragma.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data
@NoArgsConstructor
public class Imagen {
    private String id;
    private String foto;
    private Cliente cliente;
}
