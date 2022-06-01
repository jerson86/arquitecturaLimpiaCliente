package com.pragma.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Cliente {
    private long id;
    private String nombres;
    private String apellidos;
    private ETipoDocumento tipoDocumento;
    private String documento;
    private short edad;
    private String ciudadNacimiento;
}
