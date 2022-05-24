package com.pragma.cliente.dto;

import com.pragma.cliente.entity.ETipoDocumento;
import com.pragma.cliente.entity.Imagen;
import lombok.Data;
import java.time.Instant;


@Data
public class ClienteDTO{
    private long id;
    private String nombres;
    private  String apellidos;
    private ETipoDocumento tipoDocumento;
    private String documento;
    private short edad;
    private String ciudadNacimiento;
}
