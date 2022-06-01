package com.pragma.application.dto;

import lombok.Data;


@Data
public class ClienteDTO {
    private long id;
    private String nombres;
    private  String apellidos;
    private String tipoDocumento;
    private String documento;
    private short edad;
    private String ciudadNacimiento;
}
