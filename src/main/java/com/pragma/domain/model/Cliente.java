package com.pragma.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class Cliente {
    private long id;
    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String documento;
    private short edad;
    private String ciudadNacimiento;
}
