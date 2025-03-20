package com.sso.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "correlativo_orden")
public class CorrelativoOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String tipo; // solo el tipo del TipoEquipo
    private int ultimoNumero;
}
