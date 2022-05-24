package com.pragma.cliente.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
@NoArgsConstructor
@Data
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private String nombres;
    @NonNull
    private  String apellidos;
    @Enumerated(EnumType.STRING)
    private ETipoDocumento tipoDocumento;
    private String documento;
    private short edad;
    private String ciudadNacimiento;
    @CreationTimestamp
    private Instant fechaRegistro;

    //@OneToOne(mappedBy = "cliente")
    //private Imagen imagen;
}
